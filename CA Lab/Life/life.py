#############################
# @author Jack Waddell
# Conway's Game of life
# @date 2019/10/26 

import cv2
import numpy as np

T = 1000

# initialize ca based on input raster image
# @param filename: the name of the input image
# @return a 2D numpy array of the CA. 0 is dead, 1 is alive
def initCaImg(filename):
	caInitPic = cv2.imread(filename, 0)

	L0 = caInitPic.shape[0]
	L1 = caInitPic.shape[1]

	ca = np.zeros((L0,L1))

	ca[caInitPic<128]=1
	
	return ca

# initialize ca based on a random matrix
# @param L: the size of the square 2D array
# @param p: the percent density of the matrix
# @return a 2D numpy array of the CA. 0 is dead, 1 is alive
def initCaRand(L, p):
	r = np.random.random((L,L))
	ca = np.zeros((L,L))
	ca[r<p] = 1
	
	return ca

# show an image with CV2
# @param img: the image array
# @param title: the title string
# @wait
def show(img,title="image",wait=30):
  resized = cv2.resize(np.uint8(img), (600,600), interpolation = cv2.INTER_AREA)
  cv2.imshow(title, resized)
  cv2.waitKey(wait)
  
def showCA(ca, wait=30):
	L = ca.shape[0]
	out = np.zeros((L,L,3))

	out[ca==0]=(255,255,255)
	out[ca==1] =(0,0,255)
	show(out, wait=wait)


# ~ ca = initCaImg('start.bmp')
ca = initCaRand(200, 0.1)
showCA(ca, 0)

def getFit(car):
	L = car.shape[0]
	l = np.roll(car, -1, axis=0)
	r = np.roll(car, 1, axis=0)
	u = np.roll(car, 1, axis=1)
	d = np.roll(car, -1, axis=1)
	
	ul = np.roll(car, (-1,1), axis=(0,1))
	ur = np.roll(car, (1,1), axis=(0,1))
	
	dl = np.roll(car, (-1,-1), axis=(0,1))
	dr = np.roll(car, (1,-1), axis=(0,1))
	
	stk = np.stack((l, r, u, d, ul, ur, dl, dr), axis=0)
	
	fit = np.sum(stk, axis=0)
	
	return fit
	
	return stk


def update(ca, fit):
	caNext = np.copy(ca)
	caNext[np.logical_and(ca==0, fit==3)] = 1
	caNext[np.logical_and(ca==1, fit>3)] = 0
	caNext[np.logical_and(ca==1, fit<2)] = 0
	
	return caNext


for t in range(T):
	fit = getFit(ca)
	caNext = update(ca, fit)
	ca = np.copy(caNext)
	showCA(ca, 30)


