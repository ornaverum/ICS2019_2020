import cv2
import numpy as np

#colors of the two different cells
deadColor=(0,0,0)
liveColor=(255,255,255)
#values assigned to the cells
dead=0
live=1

#resizes the image
def show(img, title="image", wait=30):
	resized = cv2.resize(np.uint8(img), (600,600), interpolation = cv2.INTER_AREA)
	cv2.imshow(title, resized)
	cv2.waitKey(wait)


#creates a ca of the image and assigns the colors
def showCA(ca, wait=0):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))

    out[ca==dead]=deadColor
    out[ca==live]=liveColor
    show(out, wait=wait)


#reads the image information and assigns the cells their value
def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]<125]=dead
    out[img[:,:,1]>125]=live
    return out
    
#creates a new ca to transfer the changes to show the original is not harmed
#filters the 8 neighbors and determines the state of the cell based on the rules
def iterate(world):
    newWorld=world*1
    kernel=np.int16([[1,1,1],
                     [1,0,1],
                     [1,1,1]])
    whereThelivingAre=np.int16(world==live)
    neighborCount=cv2.filter2D(whereThelivingAre,-1,kernel,borderType=cv2.BORDER_CONSTANT)

    newWorld[np.logical_and(world==live,3<neighborCount)]=dead
    newWorld[np.logical_and(world==live,1>neighborCount)]=dead
    newWorld[np.logical_and(world==live,2==neighborCount)]=live
    newWorld[np.logical_and(world==live,3==neighborCount)]=live
    newWorld[np.logical_and(world==dead,3==neighborCount)]=live

    return newWorld


#runs the ca 1000 times, then shows the final iteration
world=load("init.bmp")
fps=30
for i in range(1000):
    showCA(world,1000//fps)
    world=iterate(world)
    
showCA(world)


