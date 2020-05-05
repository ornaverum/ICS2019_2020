import java.util.ArrayList;

public class Program{
    public static void main(String[] args){

        Building b1 = new Building("School");
        Building b2 = new Building("Pop");
        Building b3 = new Building("House2");
        Building b4 = new Building("Road 1");
        Building b5 = new Building("Road 2");
        Building b6 = new Building("House 2");
        Building b7 = new Building("Pipe line");
        Node n1 = new Node(new int[] {20, 60}, new int[] {40, 80}, new ArrayList<Node>(), b1);
        Node n2 = new Node(new int[] {70, 40}, new int[] {90, 60}, new ArrayList<Node>(), b3);
        Node n3 = new Node(new int[] {70, 70}, new int[] {80, 80}, new ArrayList<Node>(), b2);
        Node n4 = new Node(new int[] {0, 40}, new int[] {60, 50}, new ArrayList<Node>(), b4);
        Node n5 = new Node(new int[] {50, 0}, new int[] {60, 40}, new ArrayList<Node>(), b5);
        Node n6 = new Node(new int[] {20, 20}, new int[] {40, 40}, new ArrayList<Node>(), b6);
        Node n7 = new Node(new int[] {60, 40}, new int[] {100, 50}, new ArrayList<Node>(), b7);



    }
}

class Node{

    public ArrayList<Node> children;
    public int[] lb;
    public int[] ru;
    public Object obj;


    public Node(){
        children = new ArrayList<>();
        lb = new int[2];
        ru = new int[2];
        obj = null;
    }

    public Node(int[] lb, int[] ru){
        children = new ArrayList<>();
        this.lb = lb.clone();
        this.ru = ru.clone();
        obj = null;
    }

    public Node(int[] lb, int[] ru, ArrayList<Node> children){
        this.children.addAll(children);
        this.lb = lb.clone();
        this.ru = ru.clone();
        obj = null;
    }

    public Node(int[] lb, int[] ru, ArrayList<Node> children, Object obj){
        this.children.addAll(children);
        this.lb = lb.clone();
        this.ru = ru.clone();
        this.obj = obj;
    }

    public boolean containsPoint(int x, int y){
        return ((lb[0] <= x && ru[0] <= x) || (lb[1] <= y && ru[1] <= y));
    }

    public boolean boundedIn(int[] lb, int[]ru){
        return ( containsPoint(lb[0], lb[1]) && containsPoint(ru[0], ru[1]) );
    }

    public ArrayList<Object> lookup(int x, int y, Node n, ArrayList<Object> results){
        if (n==this){
            for (Node child:children){
                if (child.containsPoint(x, y))
                    lookup(x, y, child, results);
            }
        } else {
            if (containsPoint(x, y))
                results.add(obj);
        }
        return results;
    }

    public void setMBB(int[] lb, int[] ru){
        this.lb = lb.clone();
        this.ru = ru.clone();
    }

    public void setObj(Object obj){this.obj = obj;}

    public insert(int[] lb, int[] ru, Object obj, Node n){
        if (n == this){
            for(Node child:children){
                if child.boundedIn(lb, ru)
                    child.insert(lb, ru, obj, child);
            }
        }
    }

}

class Building{
    String name;

    Building(){
        name = "";
    }

    Building(String name){
        this.name = name;
    }
}
