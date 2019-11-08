import cv2
import numpy as np

liveColor=(0,0,0)
deadColor=(255,255,255)

live=1
dead=0

#shows the cellular automata 
#@param img is the defined cellular automata
#@param title is the title
#@param wait is the amount of time in milliseconds
def show(img, title="image", wait=30):
    resized = cv2.resize(np.uint8(img), (600,600), interpolation = cv2.INTER_AREA)
    cv2.imshow(title, resized)
    cv2.waitKey(wait)

#defines the cellular automata
#@param ca is the cellular automata	 
#@param wait is the amount of time in milliseconds   
def showCA(ca, wait=30):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))

    out[ca==live]=liveColor
    out[ca==dead]=deadColor
    show(out, wait=wait)


#reads an inputed image
#@param filename is the name of the file you are inserting
#@return out is the read file    
def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]==0]=live
    out[img[:,:,1]==255]=dead
    return out
    

#plays the game of life
#@param ca is the cellular automata
#@return newCA is the cellular automata after going through the game
def iterate(ca):
    newCA=ca*1
    kernel=np.int16([[1,1,1],
                     [1,0,1],
                     [1,1,1]])
    
    liveCells=np.int16(ca==live)
    neighborCount=cv2.filter2D(liveCells,-1,kernel,borderType=cv2.BORDER_CONSTANT)
    
    newCA[np.logical_and(ca==live,2>neighborCount)]=dead
    newCA[np.logical_or(np.logical_and(ca==live,2==neighborCount),np.logical_and(ca==live,3==neighborCount))]=live
    newCA[np.logical_and(ca==live,3<neighborCount)]=dead
    newCA[np.logical_and(ca==dead,3==neighborCount)]=live
    
    return newCA


ca=load("init.bmp")
fps=30
showCA(ca,0)
#animates the game (1000 iterates)
for i in range(1000):
    showCA(ca,1000//fps)
    ca=iterate(ca)
showCA(ca,0)
