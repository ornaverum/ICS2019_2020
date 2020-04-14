import pandas
import sklearn
import matplotlib.pyplot as plt
import seaborn as sns # visualization
import numpy as np
from sklearn.utils import shuffle

import tkinter as tk 
root = tk.Tk()




data=pandas.read_csv("iris.data", header=None)
print(data)

# ~ sns.pairplot( data=data,vars=(0,1,2,3), hue=4 )
# ~ plt.show()


data=np.array(data)



X=data[:,0:4] #This gets all the rows and only the first 4 columns.
y=data[:, 4]
# ~ print(X.shape) #(150,4)
# ~ print(y.shape) #(150,)


X,y=shuffle(X,y,random_state=0)


trainX=X[:100]
trainy=y[:100]

# ~ print(trainX)
# ~ print(trainy)

testX=X[100:]
testy=y[100:]

from sklearn.neural_network import MLPClassifier
clf = MLPClassifier(hidden_layer_sizes=[2,2], random_state=0, max_iter=100000)
clf.fit(trainX, trainy)

print(clf.coefs_)

# ~ print(dir(clf))
print(clf.intercepts_)

prediction=clf.predict(testX)

# ~ print(prediction[:10], testy[:10])


print(clf.score(trainX,trainy))
print(clf.score(testX,testy))

from sklearn.model_selection import cross_validate
cv_results = cross_validate(clf, X, y, cv=4)
print(cv_results)


topFrame = tk.Frame(root, width = 500, height = 1000, bg="blue", relief="ridge")
topFrame.pack(side = "top")

wordVar = tk.StringVar()

wordLabel = tk.Label(topFrame, textvariable = wordVar)
wordLabel.pack()

