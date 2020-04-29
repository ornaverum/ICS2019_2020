package preditorprey;

public class Prey extends Animal implements Critter {

  Preditor target;

  Prey(){
      super();
  }

  Prey(int x, int y){
      super(x, y);
  }

  public void act(){
    eat();
    die();
  }

  public Critter reproduce(){return null;}

  public void eat(){
    energy += 1;
  }



}
