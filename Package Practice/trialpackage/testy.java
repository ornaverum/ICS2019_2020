package trialpackage;

public class testy{
    String s;
    public testy(){
        s = "Howdy! I'm Testy!";
    }

    public String toString(){
        String s;
        s = String.format("%s = %s", this.getClass(), this.s);
        return s;
    }
}
