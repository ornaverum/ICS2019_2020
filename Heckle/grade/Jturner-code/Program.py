import cv2
import numpy as np


aliveCell=1
aliveCellColor=(0,0,0)
deadCellColor=(255,255,255)
deadCell=0


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

    out[ca==aliveCell]=aliveCellColor
    out[ca==deadCell]=deadCellColor
    show(out, wait=wait)
    
def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]<100]=aliveCell
    out[img[:,:,1]>150]=deadCell
    return out
    
def iterate(world):
    newWorld=world*1
    kernel=np.int16([[1,1,1],
                     [1,0,1],
                     [1,1,1]])
    whereTheAliveCellsAre=np.float32(world==aliveCell)
    neighborCount=cv2.filter2D(whereTheAliveCellsAre,-1,kernel,borderType=cv2.BORDER_CONSTANT)
    newWorld[np.logical_and(np.logical_and(world==aliveCell,2<=neighborCount),neighborCount<=3)]=aliveCell
    newWorld[np.logical_and(world==deadCell,3==neighborCount)]=aliveCell
    newWorld[np.logical_and(world==aliveCell,2>neighborCount)]=deadCell
    newWorld[np.logical_and(world==aliveCell,3<neighborCount)]=deadCell
    return newWorld

    
world=load("Jaden.bmp")
fps=20
while True:
    showCA(world,1000//fps)
    world=iterate(world)
