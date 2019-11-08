# pip install opencv-python

import cv2
import numpy as np


L = 50
p = 0.4
T = L*L
threshold = 0.33

p1 = p
p2 = p
# ~ np.random.seed(1011)



def show(img,title="image",wait=30):
  resized = cv2.resize(np.uint8(img), (600,600), interpolation = cv2.INTER_AREA)
  cv2.imshow(title, resized)
  cv2.waitKey(wait)
  
  
def getSatArr(car):
	L = car.shape[0]
	l = np.roll(car, -1, axis=0)
	r = np.roll(car, 1, axis=0)
	u = np.roll(car, 1, axis=1)
	d = np.roll(car, -1, axis=1)
	ul = np.roll(l, 1, axis=1)
	ur = np.roll(r, 1, axis=1)
	dl = np.roll(l, -1, axis=1)
	dr = np.roll(r, -1, axis=1)
	
	stk = np.stack((l, r, u, d, ul, ur, dl, dr), axis=0)
	
	return stk

def highlightCA(ca, i, j):
	L = ca.shape[1]
	out = np.zeros((L,L,3))
	out[ca==1] = (0,255,0)
	out[ca==2] = (0,0,255)
	if ca[i,j]==1:
		out[i,j][:] = (0,100,00)
	elif ca[i,j]==2:
		out[i,j][:] = (0,0,100)
	else:
		pass
	show(out, wait=0)


def showCA(ca, wait=30):
	L = ca.shape[1]
	out = np.zeros((L,L,3))

	out[ca==1]=(0,255,0)
	out[ca==2] =(0,0,255)
	show(out, wait=wait)

def initMat(L,p1,p2):
	count = 0
	r = np.random.rand(L,L)
	mat = np.zeros((L,L))
	mat[r<(p1+p2)]=1
	mat[r<=p1] = mat[r<=p1]+1
	return mat

	
def getSat(car):
	stk = getSatArr(car)
	
	d = stk==car
	e = stk!=0
	
	sat = np.sum(d.astype(int), axis=0)
	neigh = np.sum(e.astype(int), axis=0)
	
	neigh[sat==0]=1

	return sat/neigh
	
	
ca = np.zeros((L,L))
sat = np.zeros((L,L))

car = initMat(L, p1, p2)

print(car)

showCA(car)
satT = np.zeros(T)

sat = getSat(car)
print('Satisfaction = {}' .format(sat.sum()))



moves = 0
stall = 0
while(moves < T and stall < 1000):
	kind = 0
	while(kind == 0):
		i, j = np.random.randint(0,L,2).tolist() # select a target. We want one that isn't a 0.
		kind = car[i,j]

	stall += 1
	sat = getSat(car)
	
	if sat[i,j]<threshold:
		tKind = 1
		while(tKind != 0):
			x, y = np.random.randint(0,L,2).tolist() # select an empty site
			tKind = car[x,y]
		car[x,y] = kind
		car[i,j] = 0
		
		
		# ~ if (T%100 ==0):
			# ~ showCA(car)

		moves += 1
		stall = 0

sat = getSat(car)
print('Satisfaction = {}' .format(sat.sum()))

showCA(car, wait=0)
