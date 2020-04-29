package preditorprey;

public class Preditor extends Animal implements Critter {

  Prey target;

  Preditor(){
      super();
  }

  Preditor(int x, int y){
      super(x, y);
  }

  public void act(){
    int dx, dy;
    dx = randInt(-speed, speed);
    dy = randInt(-speed, speed);
    move(dx, dy);
    energy -= 1;
    die();
  }

  public Critter reproduce(){return null;}

  public void eat(){}

}
