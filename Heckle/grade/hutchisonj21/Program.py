import cv2
import numpy as np


aliveColor=(0,0,0)

deadColor=(255,255,255)

alive=1

dead=0


def show(img, title="image", wait=30):
    d=np.max(img.shape)
    h,w=img.shape[:2]
    unitSize=1200//d
    resized = cv2.resize(np.uint8(img), (unitSize*w,unitSize*h), interpolation = cv2.INTER_AREA)
    cv2.imshow(title, resized)
    cv2.waitKey(wait) # 0 means wait for key input. postive value waits for that many milliseconds
    
def showCA(ca, wait=0):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))

    out[ca==alive]=aliveColor
    out[ca==dead]=deadColor
    
    show(out, wait=wait)
    
def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]<100]=alive
    out[img[:,:,1]>150]=dead

    return out
    
    

def iterate(world):
    newWorld=world*1
    kernel=np.int16([[1,1,1],
                     [1,0,1],
                     [1,1,1]])
    whereTheLiveCellsAre=np.int16(world==alive)
    neighborCount=cv2.filter2D(whereTheLiveCellsAre,-1,kernel,borderType=cv2.BORDER_CONSTANT)
    newWorld[np.logical_and(neighborCount == 3, world == dead)]=alive
    newWorld[np.logical_and(neighborCount > 3, world == alive)]=dead
    newWorld[np.logical_and(neighborCount < 2, world == alive)]=dead
    newWorld[np.logical_and(np.logical_and(neighborCount >= 2, neighborCount <= 3), world == alive)]=alive
    return newWorld




world=load("ConwaysGameOfLife.png")

fps=60

while True:
    showCA(world,1000//fps)
    world=iterate(world)
