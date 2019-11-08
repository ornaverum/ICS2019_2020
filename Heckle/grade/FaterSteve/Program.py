import cv2
import numpy as np
import random as rand

false=False
true=True

aliveColor=(0,255,0)
notAliveColor=(0,0,0)

alive=1
notAlive=0
	
def show(img, title="image", wait=100):
    d=np.max(img.shape)
    if(d>800):
     d=800
    h,w=img.shape[:2]
    unitSize=800//d
    resized = cv2.resize(np.uint8(img), (unitSize*w,unitSize*h), interpolation = cv2.INTER_AREA)
    cv2.imshow(title, resized)
    cv2.waitKey(wait) # 0 means wait for key input. postive value waits for that many milliseconds
    
def showCA(ca, wait=0):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))

    out[ca==alive]=aliveColor
    out[ca==notAlive]=notAliveColor
    show(out,wait=wait)
    
def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]<89]=alive
    out[img[:,:,1]>=89]=notAlive
    return out
    
    

def iterate(world):
    newWorld=world/1
    kernel=np.int16([[1,1,1],[1,0,1],[1,1,1]])
    neighborCount=cv2.filter2D(world,-1,kernel,borderType=cv2.BORDER_CONSTANT)
    newWorld[np.logical_and(world==alive,2>neighborCount)]=notAlive #Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    newWorld[np.logical_and(world==alive,3<neighborCount)]=notAlive #Any live cell with more than three live neighbours dies, as if by overpopulation.
    newWorld[np.logical_and(world==notAlive,3==neighborCount)]=alive #Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    return newWorld

def randStart(H=100, W=100):
	ca = np.zeros( (H, W) )
	for i in range(H): #visits each column once
		for j in range(W): #visits each row once
			ca[i,j]=rand.randint(0,1)
	return ca
	
def pick1():
	try:
		rand.seed(int(input("Seed for the world generator\n\nLeave blank for a random seed\n")))
	except:
		doNothing=0
	try:
		H=int(input("How tall do you want it to be?"))
		H/5786.5
	except:
		H=100
	try:
		W=int(input("How wide to you want it to be?"))
	except:
		W=100
	world=(randStart(H, W))
	fprs(world)
	
def pick2():
	img = input("what is the exact name of your image? (for example: GameOfLife.png)\n")
	try:
		world=load(img)
		fprs(world)
	except:
		print("Failed to locate "+img+"\nWould you like to try again? Y/N")
		var = input("")
		if(var=="N" or var=="n"):
			startMenu()
		pick2()
		
def pick3():
	rand.seed(-58)
	world=(randStart(7, 7))
	fprs(world)

def startMenu():
	input("\nPress Enter When Ready.")
	pull = input("\nThere are 3 ways to initiate this game of life.\n\t1. Random world generation (with or without a seed)\n\t2. Using in your own rasterized non-vector image\n\t3. Using a neat default\n")
	if(pull=="1"):
		yn=input("Are you sure you would like to continue with a random world? (Y/N)\n")
		if(yn=="n" or yn=="N"):
			startMenu()
		pick1()
	elif(pull=="2"):
		yn=input("Are you sure you would like to continue with one of your image-based worlds? (Y/N)")
		if(yn=="n" or yn=="N"):
			startMenu()
		pick2()
	elif(pull=="3"):
		yn=input("Are you sure you would you like to continue with the default world? (Y/N)")
		if(yn=="n" or yn=="N"):
			startMenu()
		pick3()
	else:
		yn=input("Mistakes were made... Would you like to continue with the default world? (Y/N)")
		if(yn=="n" or yn=="N"):
			startMenu()
		pick3()

def fprs(world):
	try:
		fps=int(input("How many frames would you like per second? (10 is the default)"))
		if(fps<=0):
			fps=1001
		elif(fps>1000):
			fps=1000
	except:
		fps=10
	runProg(world,fps)

def runProg(world,fps):
	while true:
		showCA(world,1000//fps)
		world=iterate(world)
    
    
print("Hello, and welcome to Conway's Game Of Life.")
startMenu()
