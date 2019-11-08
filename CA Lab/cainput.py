# pip install opencv-python

import cv2
import numpy as np


caInitPic = cv2.imread('init.bmp', 0) # the 0 says to import the image in grayscale

L0 = caInitPic.shape[0] # get the height and width of the image if it's not a square
L1 = caInitPic.shape[1]

ca = np.zeros((L0,L1))


# anything closer to black than to white in the image becomes a 1. Otherwise it stays 0.
ca[caInitPic<128]=1	

print(ca)


def show(img, title="image", wait=30):
	resized = cv2.resize(np.uint8(img), (600,600), interpolation = cv2.INTER_AREA)
	cv2.imshow(title, resized)
	cv2.waitKey(wait) # 0 means wait for key input. postive value waits for that many milliseconds
	
def showCA(ca, wait=30):
	L = ca.shape[1]
	out = np.zeros((L,L,3))

	out[ca==1]=(0,255,0)
	# ~ out[ca==2] =(0,0,255)
	show(out, wait=wait)
	

showCA(ca,0)
