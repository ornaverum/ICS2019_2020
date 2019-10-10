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

  
  # ~ cv2.destroyAllWindows()

def initMat(L,p1,p2):
	count = 0
	r = np.random.rand(L,L)
	mat = np.zeros((L,L))
	mat[r<(p1+p2)]=1
	mat[r<=p1] = mat[r<=p1]+1
	return mat

def initMat2(L,p1,p2):
	count = 0
	mat = np.zeros((L,L))
	for i in range(L):
		for j in range(L):
			r = np.random.rand()
			if r < p1:
				mat[i,j] = 1
				count += 1
			elif r < p1 + p2:
				mat[i,j] = 2
				count += 1
			else:
				pass
	print ("Count = {} out of {} which is {}" .format(count, L*L, count/(L*L)))
	return mat




def checkHood(car, i, j, kind):
	L = car.shape[1]
	count = 0
	
	
	# ~ print("Check neighboorhood of ({},{}), which is kind {}" .format(i, j, kind))
	if kind == car[(i+1)%L,j]:
		# ~ print("right")
		count += 1
	if kind == car[(i-1)%L,j]:
		# ~ print("left")
		count += 1
	if kind == car[i,(j+1)%L]:
		# ~ print("up")
		count += 1
	if kind == car[i,(j-1)%L]:
		# ~ print("down")
		count += 1
	
	if kind == car[(i+1)%L,(j+1)%L]:
		# ~ print("up right")
		count += 1
	if kind == car[(i+1)%L,(j-1)%L]:
		# ~ print("down right")
		count += 1
	if kind == car[(i-1)%L,(j+1)%L]:
		# ~ print("left up")
		count += 1
	if kind == car[(i-1)%L,(j-1)%L]:
		# ~ print("left down")
		count += 1
	
	# ~ print("Neighboor count = {} which is {}%" .format(count, count/8*100))
	# ~ highlightCA(car,i,j)
	
	return count/8
	
def getSat(car):
	l = np.roll(car, -1, axis=0)
	r = np.roll(car, 1, axis=0)
	u = np.roll(car, 1, axis=1)
	d = np.roll(car, -1, axis=1)
	ul = np.roll(l, 1, axis=1)
	ur = np.roll(r, 1, axis=1)
	dl = np.roll(l, -1, axis=1)
	dr = np.roll(r, -1, axis=1)
	
	sat = (car==l).astype(int) + (car==r).astype(int) + (car==u).astype(int) + (car==d).astype(int) \
	+(car==ul).astype(int) + (car==ur).astype(int) + (car==dl).astype(int) + (car==dr).astype(int)
	
	sat = sat/8

	return sat
	
	
def getSat2(car):
	L = car.shape[1]
	sat = np.zeros((L,L))
	for i in range(L):
		for j in range(L):
			sat[i,j] = checkHood(car,i,j)
	return sat
	
def initMat3(L, p1, p2):
	mat = np.zeros((L,L))
	for i in range(L):
		for j in range(L):
			if (i+j)%2==0:
				mat[i,j]=1
	return mat
	
ca = np.zeros((L,L))

sat = np.zeros((L,L))


# ~ car = (np.random.rand(L,L)<p).astype(int)

car = initMat(L, p1, p2)

print(car)

showCA(car)
satT = np.zeros(T)

sat = getSat(car)
print('Satisfaction = {}' .format(sat.sum()))

moves = 0
stall = 0
while(moves < T and stall < 1000):
	# ~ print(t)
	kind = 0
	while(kind == 0):
		# ~ print('Finding target')
		i, j = np.random.randint(0,L,2).tolist() # select a target. We want one that isn't a 0.
		kind = car[i,j]

	stall += 1
	sat = getSat(car)
	
	# ~ if sat[i,j]<threshold:
	if checkHood(car, i, j, kind)<threshold:
		tKind = 1
		while(tKind != 0):
			# ~ print('Finding destination')
			x, y = np.random.randint(0,L,2).tolist() # select an empty site
			tKind = car[x,y]
		# ~ print("The satisfaction at ({},{})is {}" .format(i,j,checkHood(car,i,j,kind)))
		car[x,y] = kind
		car[i,j] = 0
		
		# ~ showCA(car)

		moves += 1
		stall = 0
	
	
	

	# ~ sat = getSat2(car)

	# ~ if sat[x,y]>=threshold:
	

	# ~ satT[t] = sat.sum()


sat = getSat(car)
print('Satisfaction = {}' .format(sat.sum()))
	

showCA(car, wait=0)
