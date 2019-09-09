import turtle, math



def fahrToCel (fahr_deg_in):
	cel_deg_out = (fahr_deg_in-32)*5/9
	print("{} degrees Fahrenheit is {} degrees Celcius" .format(fahr_deg_in, cel_deg_out))
	
def acreToBarn (acres_in):
	BARN_PER_SQM = 1.e-28
	ACRE_PER_SQM = 4047
	BARN_PER_ACRE = BARN_PER_SQM/ACRE_PER_SQM
	barns_out = acres_in/BARN_PER_ACRE
	
	print("There are {} barns in {} acres. You could grow a lot of nuclei in that." .format(barns_out, acres_in))

def drawPoly (num_sides):
	don = turtle.Turtle()
	for i in range(num_sides):
		don.forward(800/num_sides)
		don.left(180-(num_sides-2)*180/num_sides)
	screen = don.getscreen()
	screen.mainloop()



def drawPolyRec(num_sides, num):
	if(num==0):
		return
	else:
		don.forward(800/num_sides)
		don.left(180-(num_sides-2)*180/num_sides)
		drawPolyRec(num_sides, num-1)
		




fahrToCel(float(input("How many degrees Fahrenheit would you like to convert to Celcius?: ")))

acreToBarn(float(input("\n\n\n\nHow many acres would you like to convert to barns?: ")))



num_sides = int(input("\n\n\n\nHow many sides would you like your polygon to have?: "))

don = turtle.Turtle()
drawPolyRec(num_sides, num_sides)

screen = don.getscreen()
screen.mainloop()
