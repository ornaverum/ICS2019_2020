
package com.ics.preditorprey;

class agent {
		int x, y;
		int energy;
		public int reproductionThresholdEnergy;
		public double visionRadius;
		public boolean seesTarget;
		public int speed;
		ecosystem eco;

		agent(){
			x = 0;
			y = 0;
			energy = 0;
			reproductionThresholdEnergy = 0;
			visionRadius = 0;
			speed = 1;
			seesTarget = false;
		}

		agent(int x, int y, int e){
			this.x = x;
			this.y = y;
			energy = e;
			reproductionThresholdEnergy = 1000;
			visionRadius = 5;
			this.speed = 1;
			seesTarget = false;
		}

		public void setPos(int x, int y){
			this.x = x;
			this.y = y;
		}

		public int[] getPos(){
			int[] pos = {x, y};
			return pos;
		}

		public int getEnergy(){	return energy;}

		public void setEnergy(int e) { energy = e; }

		public String toString(){
			String s = String.format("Type: %s at pos = (%d, %d) with energy = %d.", this.getClass(), x, y, energy);
			return s;
		}

		public boolean seeAgent(agent target){
				int[] posTarget;
				int xT, yT;

				posTarget = target.getPos();
				xT = posTarget[0];
				yT = posTarget[1];
				double distance = Math.hypot(x-xT, y-yT);
				return (distance <= visionRadius);
		}

		public static int randIntRange(int min, int max){
			Random rand = new Random();
			int r = rand.nextInt( (max-min) + 1) + min;
			return r;
		}

		public int[] getRandomMove(int speed){
				int rx, ry;
				rx = randIntRange(-speed, speed);
				ry = randIntRange(-speed, speed);
				int[] dr = {rx, ry};
				return dr;
		}

		public void step(int dx, int dy){

			int x0 = x;
			int y0 = y;

			int y1, x1;

			int maxX = eco.maxX;
			int maxY = eco.maxY;

			if (x0 + dx >= maxX){
				x1 = x0 + dx - maxX;
			} else if (x0 + dx < 0) {
				x1 = x0 + dx + maxX;
			} else {
				x1 = x0 + dx;
			}

			if (y0 + dy >= maxY){
				y1 = y0 + dy - maxY;
			} else if (y0 + dy < 0) {
				y1 = y0 + dy + maxY;
			} else {
				y1 = y0 + dy;
			}
			setPos(x1, y1);
		}



}
