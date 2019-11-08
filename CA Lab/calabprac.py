import cv2
import numpy as np

L = 20
p = 0.4
p1 = p
p2 = p

np.random.seed(100)

neighborsList = ((-1,-1), (-1,0) ,(-1,1), (0,-1), (0, 1), (1,-1), (1,0), (1,1))

# Display an array
# @param img the array
# @param title
# @param wait = the wait time in milliseconds. 0 to wait for keypress
def show(img, title="image", wait=30):
	resized = cv2.resize(np.uint8(img), (600,600), interpolation = cv2.INTER_AREA)
	cv2.imshow(title, resized)
	cv2.waitKey(wait) # 0 means wait for key input. postive value waits for that many milliseconds

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
	
	print("Kind is {}. There are {} likes and {} neighbors." .format(kind, likeCount, neighCount))
	
	if (neighCount > 0):
		return likeCount/neighCount
	else:
		return 0

ca = initCA(L, p1, p2)
print(ca)

print(calcSat(ca, 4, 1))


t = 0
while(t < T):
	
	kind = 0
	while(kind == 0):
		i, j = np.random.randint(0, L, 2).tolist()
		kind = car[i, j]
	sat = calcSat(car, i, j)
	k = 1
	if sat < threshold:
		while (tKind != 0):
			x, y = np.random.randint(0, L, 2).tolist()
			tKind = ca[x, y]
		(ca[i, j], ca[x, y]) = (ca[x,y], ca[i, j])
	
	t += 1


