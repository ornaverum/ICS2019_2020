#Eric Chrisman | ICS 2019/2020
import cv2
import numpy as np

#Sets up veribles
aliveColor = (0)
deadColor = (255)

alive = 1
dead = 0

#/////////////////////////////////////////////////////////////////////////////////////////////////////

#This is what prints out the picture
def show(img, title="image", wait=30):
    d = np.max(img.shape)
    h, w = img.shape[:2]
    unitSize = 1200//d
    resized = cv2.resize(np.uint8(img), (unitSize*w,unitSize*h), interpolation = cv2.INTER_AREA)
    cv2.imshow(title, resized)
    cv2.waitKey(wait) 
	
def showCA(ca, wait=0):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))

    out[ca == alive] = aliveColor
    out[ca == dead] = deadColor
    show(out, wait = wait)
    
#//////////////////////////////////////////////////////////////////////////////////////////////////////

#This loads in the picture and idenfies what it shows
def load(filename):
    img = cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:, :, 1]<100] = alive
    out[img[:, :, 1]>=150] = dead
    return out
    
#This updates the pictures while keeping the old picture
def iterate(town):
	print(town)
	newGenTown = town * 1
	kernel=np.int16([[1 ,1 ,1], [1, 0, 1], [1, 1, 1]])
	theLastOfUs = np.int16(town == alive)
	neighborCount = cv2.filter2D(theLastOfUs,-1,kernel,borderType=cv2.BORDER_CONSTANT)
	newGenTown[np.logical_and(town == alive, 2 > neighborCount)] = dead
	newGenTown[np.logical_and(town == alive, 3 < neighborCount)] = dead
	newGenTown[np.logical_and(town == dead, 3 == neighborCount)] = alive
	return newGenTown


#Loads the picture to be used as data.
town = load("init.png")
fps=10
while True:
    showCA(town,1000//fps)
    town=iterate(town)
#//////////////////////////////////////////////////////////////////////////////////////////////////////




	




