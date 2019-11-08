"""
Kaden Ramirez
This program creates a cellular automata that follows
Conways game of life rules. Using your own image created
in a program such as paint, this program will make a
cellular automata.
"""



import cv2
import numpy as np

liveCell = 1
liveCellColor = (255,255,255)
deadCell = 0
deadCellColor = (0,0,0)

#displays the CA using the paramaters set by showCA
def show(img, title="image", wait=30):
    d=np.max(img.shape)
    h,w=img.shape[:2]
    unitSize=600//d
    resized = cv2.resize(np.uint8(img), (unitSize*w,unitSize*h), interpolation = cv2.INTER_AREA)
    cv2.imshow(title, resized)
    cv2.waitKey(wait) # 0 means wait for key input. postive value waits for that many milliseconds

#establishes the basics of the canvas
#initalizes the live and dead cell colors
def showCA(ca, wait=0):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))

    out[ca==liveCell]=liveCellColor
    out[ca==deadCell]=deadCellColor
    show(out, wait=wait)

#creates the CA by loading the picture
def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]<100]=deadCell
    out[img[:,:,1]>150]=liveCell
    return out
    
#recreates CA using the rules of conways game of life
def iterate(world):
    newWorld=world*1
    kernel=np.int16([[1,1,1],
                     [1,0,1],
                     [1,1,1]])
    whereTheLiveCellsAre=np.int16(world==liveCell)
    neighborCount=cv2.filter2D(whereTheLiveCellsAre,-1,kernel,borderType=cv2.BORDER_CONSTANT)
    newWorld[np.logical_and(np.logical_and(world==liveCell,2<=neighborCount), neighborCount<=3)]=liveCell
    newWorld[np.logical_and(world==deadCell,3==neighborCount)]=liveCell
    newWorld[np.logical_and(world==liveCell, 2>neighborCount)]= deadCell
    newWorld[np.logical_and(world==liveCell,3<neighborCount)]=deadCell
    return newWorld

#loads the selected picture
world=load("CA2.bmp")
fps=30

#infinity loads the world until the programer stops it
while True:
    showCA(world,1000//fps)
    world=iterate(world)
