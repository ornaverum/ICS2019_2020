# Timothy Tarca
# 10/29/19
# Lab 01: Conway's Game of Life

import cv2
import numpy as np

aliveCellColor=(255,255,255)
deadCellColor=(0,0,0)
deadCell=0
aliveCell=1

#Display an array
# @param world is the array
# @param title
# @param wait = the wait time in milliseconds. 0 to wait for keypress
def show(world, title="image", wait=30):
    d=np.max(world.shape)
    h,w=world.shape[:2]
    unitSize=1200//d
    resized = cv2.resize(np.uint8(world), (unitSize*w,unitSize*h), interpolation = cv2.INTER_AREA)
    cv2.imshow(title, resized)
    cv2.waitKey(wait)
    
def showCA(ca, wait=0):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))
    out[ca==aliveCell]=aliveCellColor
    out[ca==deadCell]=deadCellColor
    show(out, wait=wait)

# Takes in image
def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]<100]=deadCell
    out[img[:,:,1]>150]=aliveCell
    return out

# Updates the motions of the cells
# @param world is the array
def iterate(world):
    newWorld=world*1
    # Determines the amount of live cells
    kernel=np.int16([[1,1,1],
                     [1,0,1],
                     [1,1,1]])
    whereTheAliveCellsAre=np.int16(world==aliveCell)
    neighborCount=cv2.filter2D(whereTheAliveCellsAre,-1,kernel,borderType=cv2.BORDER_CONSTANT)
    
    # Rules guiding the game
    newWorld[np.logical_and(world==aliveCell,neighborCount<2)]=deadCell
    newWorld[np.logical_and(np.logical_and(world==aliveCell,2<=neighborCount),neighborCount<=3)]=aliveCell
    newWorld[np.logical_and(world==aliveCell,neighborCount>3)]=deadCell
    newWorld[np.logical_and(world==deadCell,neighborCount==3)]=aliveCell
    return newWorld

# Initiates the game
world=load("world.png")
framesPerSecond=30
while True:
    showCA(world,1000//framesPerSecond)
    world=iterate(world)
