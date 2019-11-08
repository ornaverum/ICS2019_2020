# ~ 
 # ~  John Conway's Game of Life Program
 # ~  
 # ~  @author Tyler Kuper
 # ~  version 0.3
 # ~  since 2019-10-30
 # ~ 


import cv2
import numpy as np

dead = 0
alive = 1

# Displays array
# @param img is the array
# @param title
# @param wait = the wait time in milliseconds.
def show(img, title="image", wait=30):
    d=np.max(img.shape)
    h,w=img.shape[:2]
    unitSize=1200//d
    resized = cv2.resize(np.uint8(img), (unitSize*w,unitSize*h), interpolation = cv2.INTER_AREA)
    cv2.imshow(title, resized)
    cv2.waitKey(wait) 
    
# Displays Cellular Automata
# @param ca is cellular automata
# @param wait = the wait time in milliseconds.
def showCA(ca, wait=0):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))
    out[ca==alive]=(255,0,0)
    out[ca==dead]=(128,128,128)
    show(out,wait=wait)

# Loads image file
# @param filename
# @return image with alive and dead cells
def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]<100]=alive
    out[img[:,:,1]>150]=dead
    return out

# Iterates the cellular automata
# @param conway is the image that is being iterated
# @return newConway using Game of Life rules
def iterate(conway):
	newConway = conway*1
	kernel=np.int16([[1,1,1],
                     [1,0,1],
                     [1,1,1]])
	aliveLocation=np.int16(conway==alive)
	neighborCount=cv2.filter2D(aliveLocation,-1,kernel,borderType=cv2.BORDER_CONSTANT)
	newConway[np.logical_and(conway==alive,neighborCount<=1)]=dead
	newConway[np.logical_and(np.logical_and(conway==alive,neighborCount>=2),neighborCount<=3)]=alive
	newConway[np.logical_and(conway==alive,neighborCount>=4)]=dead
	newConway[np.logical_and(conway==dead,neighborCount==3)]=alive
	newConway[np.logical_and(conway==dead,neighborCount!=3)]=dead
	return newConway

#Loads png file into array and iterates ca
# GAMEOFLIFE.png consists random patterns
# GUN.png is a pattern that shoots out gliders
# SPACESHIP.png is full of patterns like spaceships that rise and explode.
# STARS.png is full of blinking patterns like stars

conway=load("GAMEOFLIFE.png")
fps=40
for i in range(200):
    showCA(conway,1000//fps)
    conway=iterate(conway)
conway=load("GUN.png")
fps=30
for i in range(150):
    showCA(conway,1000//fps)
    conway=iterate(conway)
conway=load("STARS.png")
fps=20
for i in range(100):
    showCA(conway,1000//fps)
    conway=iterate(conway)
conway=load("SPACESHIP.png")

fps=30
for i in range(175):
    showCA(conway,1000//fps)
    conway=iterate(conway)




