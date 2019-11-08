import cv2
import numpy as np

L = 20
p = 0.4
p1 = p
p2 = p

thresh = 0.33

T = 10000

np.random.seed(100)

# ! von Neumann neighborhood
# Moore
neighborsList = ((-1,-1), (-1,0) ,(-1,1), (0,-1), (0, 1), (1,-1), (1,0), (1,1))


# Display an array
# @param img the array
# @param title
# @param wait = the wait time in milliseconds. 0 to wait for keypress
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
	


# initialize celluar automata
# @param L is the size of the square array
# @param p1 is the percent density of population 1
# @param p2 is the percent density of population 2
# @return the celcular automata array
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


# calculate the satisfaction of an element of the ca
# @param ca is the ca
# @param i, j are the indices
# return the satisfaction
def calcSat(ca, i, j):
	kind = ca[i,j]
	likeCount = 0
	neighCount = 0

	for x,y in neighborsList:
		neighborKind = ca[(i+x)%L, (j+y)%L]
		if(kind==neighborKind):
			likeCount +=1
		if(neighborKind!=0):
			neighCount +=1
	
	# ~ print("Kind is {}. There are {} likes and {} neighbors." .format(kind, likeCount, neighCount))
	
	if (neighCount > 0):
		return likeCount/neighCount
	else:
		return 0

ca = initCA(L, p1, p2)

showCA(ca, wait=0)


t = 0

while(t<T):
	kind = 0
	# Look for a random non-empty space (i,j)
	while(kind == 0):
		i, j = np.random.randint(0, L, 2).tolist()
		# ~ print(i,j)
		kind = ca[i,j]

	# See if that space has an unsatisfied occupant
	if calcSat(ca, i, j) < thresh:
		targetKind = 1
		#Look for an empty space (x,y)
		while(targetKind != 0):
			x, y = np.random.randint(0, L, 2).tolist()
			targetKind = ca[x,y]
			
		# swap (i,j) and (x,y)
		ca[i,j], ca[x,y] = ca[x,y], ca[i,j]
	
	# ~ showCA(ca, wait=30)		
	t += 1

showCA(ca, wait=0)	

