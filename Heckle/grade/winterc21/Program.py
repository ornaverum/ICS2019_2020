import cv2
import numpy as np

live=1
liveColor=(255,0,0)
dead=1
deadColor=(0,0,0)


neighborsList = ((-1,-1), (-1,0) ,(-1,1), (0,-1), (0, 1), (1,-1), (1,0), (1,1))


def show(img, title="image", wait=30):
    d = np.max(img.shape)
    h,w = img.shape[:2]
    unitSize = 600//d
    resized = cv2.resize(np.uint8(img), (unitSize*w,unitSize*h), interpolation = cv2.INTER_AREA)
    cv2.imshow(title, resized)
    cv2.waitKey(wait)


def showCA(ca, wait=0):
    h,w = ca.shape[:2]
    out = np.zeros((h,w,3))

    out[ca==1]=(255,0,0)
    out[ca==0]=(0,0,0)
    show(out, wait=wait)


def load(filename):
    img=cv2.imread(filename)
    h,w = img.shape[:2]
    out = np.zeros((h,w))
    out[img[:,:,1]>150]=0
    out[img[:,:,1]<100]=1
    return out




def iterate(ca):
    newCa = ca*1
    h,w = ca.shape
    for i in range(w):
        for j in range(h):
            n = 0
            v = ca[i][j]
            for dx,dy in neighborsList:
                ti=(i+dx)%w
                tj=(j+dy)%w
                if ca[ti][tj]==1:
                    n+=1
                #look around ca[i][j] and count neighbors

            if v==1 and n<2:
                newCa[i][j]=0
            elif v==1 and n>=2 and n<=3:
                newCa[i][j]=1
            elif v==0 and n==3:
                newCa [i][j]=1
            elif v==1 and n>3:
                newCa[i][j]=0
            elif v==0 and n<3 or n>3:
                newCa [i][j]=0
    return newCa
    


ca=load("CA_1.png")
fps=10
while True:
    showCA(ca,1000//fps)
    ca=iterate(ca)
