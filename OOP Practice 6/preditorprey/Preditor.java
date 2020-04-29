package preditorprey;

public class Preditor extends Animal implements Critter {

  Prey target;

  Preditor(){
      super();
      type = 1;
  }

  Preditor(int id){
      super(id);
      type = 1;
  }

  Preditor(int x, int y, int id){
      super(x, y, id);
      type = 1;
  }

  Preditor(int x, int y, int id, int type){
      super(x, y, id, type);
  }



  public void act(){
    // int dx, dy;
    // dx = randInt(-speed, speed);
    // dy = randInt(-speed, speed);
    // move(dx, dy);
    super.act();
    energy -= 1;
    eat();
    die();
  }

  public void reproduce(){}

  public void eat(){
      eatNeighbor();
  }

  public boolean eatNeighbor(){
      int pos[] = eco.getRandomNeighbor(x, y, 2);
      if (pos == null){
          return false;
      } else {
          System.out.printf("Received {%d, %d}", pos[0], pos[1]);
          int targetID = eco.idGrid[pos[0]][pos[1]];
          System.out.printf("ID = %d\n", eco.idGrid[pos[0]][pos[1]]);
          Prey target = (Prey) eco.animalList.get(targetID);
          eco.killList.add(target);
          energy += 25;
          return true;
      }

  }

}
