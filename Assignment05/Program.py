''' hilo
@author Jack Waddell
This plays a game of hilo with the user
'''

import random

def hilo(target, guess):
	if target == guess:
		print("Winner, winner, chicken dinner! The target was {}!" .format(target))
		return
	elif guess < target:
		guess = int(input("Too slow, too low. Whatcha gonna do now? "))
		hilo(target, guess)
	else:
		guess = int(input("Too high, smart guy. Give another try: "))
		hilo(target, guess)

lo_val = int(input("We're going to play an integer guessing game. What would you like the lowest possible value to be? "))
hi_val = int(input("What would you like the highest possible value to be? "))

target = random.randint(lo_val, hi_val)
guess = int(input("I've picked a number on the interval [{}, {}]. Take a guess: " .format(lo_val, hi_val)))
hilo(target, guess)

