class Node:
	def __init__(self, weights, offset):
		self.weights = weights
		self.offset = offset
	
	# map inputs v_i's (0's and 1's) from input list x and weights to an output 0 or 1
	def eval(self, inputList):
		total = self.offset
		for w,v in zip(self.weights, inputList):
			total += w*v
		return 1 if total > 0 else 0 #ternary

ned = Node([-0.5, -0.5], 0)
print(ned.eval([1, 1])) # need 0
print(ned.eval([1, 0])) # need 1
print(ned.eval([0, 1])) # need 1
print(ned.eval([0, 0])) # need 0

# use many nodes (maybe 3 or more) to make x04


