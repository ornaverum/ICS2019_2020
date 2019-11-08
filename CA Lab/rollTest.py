import cv2
import numpy as np

L = 20
p = 0.4
p1 = p
p2 = p

thresh = 0.33

T = 10000

np.random.seed(100)


def show(img, title="image", wait=30):
	resized = cv2.resize(np.uint8(img), (600,600), interpolation = cv2.INTER_AREA)
	cv2.imshow(title, resized)
	cv2.waitKey(wait) # 0 means wait for key input. postive value waits for that many milliseconds

def showCA(ca, title="image", wait=30):
	L = ca.shape[0]
	img = np.zeros((L,L,3))
	img[ca==1]=(0,0,255)
	img[ca==2]=(255,0,0)
	show(img, title = title, wait=wait)
	
	
def initCA(L, p1, p2):
	ca = np.zeros( (L, L) )
	for i in range(L): #visits each column once
		for j in range(L): #visits each row once
			r = np.random.random()
			if (r>= p1+p2):
				ca[i,j]=0
			elif(p1<=r<p1+p2):
				ca[i,j]=2
			else:
				ca[i,j]=1
	return ca


ca = initCA(L, p, p)

showCA(ca, wait = 0)

# ~ showCA(np.roll(ca, 1, axis=0), title = 'roll down', wait = 0)

# ~ showCA(np.roll(ca, -1, axis=0), title = 'roll up', wait = 0)

# ~ showCA(np.roll(ca, 1, axis=1),  wait = 0)

u = np.roll(ca, -1, axis=0)

showCA(np.roll(u, 1, axis=1), wait = 0)



