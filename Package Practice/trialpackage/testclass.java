package trialpackage;

public class testclass{
  int k;
  public testclass(){
      k = 10;
  }
  public String toString(){
      String s;
      s = String.format("%s = %d", this.getClass(), k);
      return s;
  }

}
