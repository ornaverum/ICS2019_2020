package preditorprey;

public class ecosystem{
	public int maxX, maxY;
	public double preyDensity, predDensity;
	public ArrayList<agent> masterList = new ArrayList<>();
	public ArrayList<agent> babyList = new ArrayList<>();
	public int[][] displayGrid;
	public int[][] world;

	public ecosystem(){
	}

	public ecosystem(int maxX, int maxY){
		this.maxX = maxX;
		preyDensity = 0.01;
		predDensity = 0.005;
		this.maxY = maxY;
		populate();
		displayGrid = new int[maxX][maxY];
		world = new int[maxX][maxY];
		updateGrid();
		// growup();
		drawGrid();
	}

	public String toString(){
		String s = "";
		for(agent c: masterList){
			s += String.format("%s\n", c.toString());
		}
		return s;
	}

	public void populate(){
		Random rand = new Random();
		double r;
		agent c;
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){
				r = rand.nextDouble();
				if (r < preyDensity){
						c = addNewHerbovore(i,j);
				} else if (r < preyDensity + predDensity) {
						c = addNewCarnivore(i,j);
				}
			}
		}
		growup();
	}

	public herbovore addNewHerbovore(int i, int j){
		herbovore c = new herbovore(i, j, 1);//, 5, 5.0, 1);
		c.eco = this;
		c.speed = 1;
		c.reproductionThresholdEnergy = 5;
		c.visionRadius = 8;
		babyList.add(c);
		return c;
	}

	public carnivore addNewCarnivore(int i, int j){
		carnivore c = new carnivore(i, j, 1);//, 2, 5.0, 2);
		c.eco = this;
		c.speed = 2;
		c.reproductionThresholdEnergy = 2;
		c.visionRadius = 5;
		babyList.add(c);
		return c;
	}

	public void growup(){
		masterList.addAll(babyList);
		babyList.clear();
	}

	public void updateGrid(){
		String s;
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){
				displayGrid[i][j] = 0;
				world[i][j] = -1;
			}
		}
		int k = 0;
		for (agent c:masterList){
			int[] pos = c.getPos();
			// s = String.format("%s", c.getClass());
			// System.out.println(s);
			int i = pos[0];
			int j = pos[1];
			displayGrid[i][j]= (c instanceof herbovore)?1:2;
			world[i][j] = k;
			k++;
			// String type = String.format("%s", f.getClass());
			// if(type.contains("grass")){
			// 	displayGrid[i][j] = "g";
			// } else {
			// 	displayGrid[i][j] = "X";
			// }
		}
	}

	public void drawGrid(){
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){
				// System.out.printf( "%d", displayGrid[i][j] );
				System.out.printf( "%d", world[i][j] );
			}
			System.out.printf("\n");
		}
	}




	public static int randIntRange(int min, int max){
		Random rand = new Random();
		int r = rand.nextInt( (max-min) + 1) + min;
		return r;
	}

	public void nextCycle(){
		Random rand = new Random();
		int rx, ry;
		//findTargets();
		for(agent c: masterList){
				if (c instanceof carnivore){
					((carnivore) c).act();
				}
				else if (c instanceof herbovore){
					((herbovore) c).act();
				} else {}
		}

		growup();

		for(agent c: masterList){
				c.updatePos();
		}
	}


	public double getDistance(int[] pos1, int[] pos2){
			double x1, x2, y1, y2;
			x1 = (double) pos1[0]; y1 = (double) pos1[1];
			x2 = (double) pos2[0]; y2 = (double) pos2[1];
			if ( (x2 - x1)*2 > maxX) x2 = maxX - x2;
			if ( (y2 - y1)*2 > maxY) y2 = maxY - y2;
			return Math.hypot(x1-x2, y1-y2);
	}

	public int mod(int x, int max){
			if (x > max) return x - max;
			else if (x < 0) return x + max;
			else return x;
	}


	public void setTargets(){
			int r;
			double dMax;
			for (int i = 0; i < maxX; i++){
					for (int j = 0; i < maxY; j++){
							if (world[i][j]>=0){
									agent c = masterList.get(world[i][j]);
									r = (int) Math.ceil(c.visionRadius);
									dMax = 2*r;
									for (int m = i - r; m < i + r; m ++){
											for (int n = j - r; n < j + r; n ++){

											}
									}
							}
					}
			}
	}

}
