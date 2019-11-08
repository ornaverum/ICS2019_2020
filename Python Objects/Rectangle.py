import math

class Rectangle:
	def __init__(self, w,h):
		self.w=w
		self.h=h
	
	def area(self):
		return self.w*self.h

	def __mul__(self, other): #multiply rectangles
		return Rectangle(self.w*other.w, self.h*other.h)
		
		
	def __str__ (self):
		return ('I am a rectangle with w = {} and h = {}' .format(10, 20))
		
	def distance(x1, y1, x2, y2):
		return math.hypot(x1-x2, y1-y2)
	

a = Rectangle(10, 20)
b = Rectangle(2, 3)

print(a)
print(b)
print(a*b)

print(Rectangle.distance(2, 4, 8, 4))
