package preditorprey;

public class Prey extends Animal implements Critter {

  Preditor target;
  public boolean graze;

  Prey(){
      super();
      type = 2;
  }

  Prey(int id){
      super(id);
      type = 2;
  }

  Prey(int x, int y, int id){
      super(x, y, id);
      type = 2;
  }

  Prey(int x, int y, int id, int type){
      super(x, y, id, type);
  }

  public void act(){
    // graze = !graze;
    // if (graze)
      eat();
    // else
      super.act();
    reproduce();
    die();
  }

  public void reproduce(){
      super.reproduce();
  }

  public void eat(){
    energy += 1;
  }



}
