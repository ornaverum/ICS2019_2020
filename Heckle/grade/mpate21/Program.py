import cv2
import numpy as np
# ~ caInitPic = cv2.imread(world, 0) # the 0 says to import the image in grayscale

aliveColor=(255,255,255)
deadColor=(0,0,0)

alive=1
dead=0

# ~ L0 = caInitPic.shape[0] # get the height and width of the image if it's not a square
# ~ L1 = caInitPic.shape[1]
# ~ print(L0)
# ~ print(L1)
# ~ ca = np.zeros((L0,L1))


# anything closer to black than to white in the image becomes a 1. Otherwise it stays 0.
# ~ ca[caInitPic<128]=1
# ~ ca[caInitPic>=128]=0



def show(img, title="image", wait=30):
	d=np.max(img.shape)
	h,w=img.shape[:2]
	unitSize=1200//d
	resized = cv2.resize(np.uint8(img), (unitSize*h,unitSize*w), interpolation = cv2.INTER_AREA)
	cv2.imshow(title, resized)
	cv2.waitKey(wait) # 0 means wait for key input. postive value waits for that many milliseconds
	
	
	
def showCA(ca, title="image",wait=30):
	# ~ #L = ca.shape[1]
	h,w= ca.shape[:2]
	out = np.zeros((h,w,3))
	out[ca==1]=aliveColor
	out[ca==0]=deadColor
	show(out, wait=wait)
def load(filename):
	img=cv2.imread(filename)
	h,w=img.shape[:2]
	out = np.zeros((h,w))
	out[img[:,:,1]<100]=alive
	out[img[:,:,1]>=150]=dead	
	return out	
def iterate(world):
	newWorld=world/1
	kernel=np.int16([[1,1,1],[1,0,1],[1,1,1]])
	neighborCount=cv2.filter2D(world,-1, kernel, borderType=cv2.BORDER_CONSTANT)
	newWorld[np.logical_and(world==alive,2>neighborCount)]=dead
	newWorld[np.logical_and(world==alive,3<neighborCount)]=dead
	newWorld[np.logical_and(world==dead,3==neighborCount)]=alive
	return(newWorld)
# ~ def load():
	# ~ img="Conways.png"
	# ~ try:
		# ~ world=load(img)
		# ~ iterate(world)
	# ~ except:
		# ~ doNothing=0
		

world=load("Conways.png")
#world=np.array([[0,0,0],[0,1,1],[0,0,0]])
fps=100
while True:
	showCA(world,1000//fps)
	world=iterate(world)

