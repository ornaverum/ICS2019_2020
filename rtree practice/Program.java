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
        Node n1 = new Node(new Point(20, 60), new Point(40, 80), new ArrayList<Node>(), b1);
        Node n2 = new Node(new Point(70, 40), new Point(90, 60), new ArrayList<Node>(), b3);
        Node n3 = new Node(new Point(70, 70), new Point(80, 80), new ArrayList<Node>(), b2);
        Node n4 = new Node(new Point(0, 40), new Point(60, 50), new ArrayList<Node>(), b4);
        Node n5 = new Node(new Point(50, 0), new Point(60, 40), new ArrayList<Node>(), b5);
        Node n6 = new Node(new Point(20, 20), new Point(40, 40), new ArrayList<Node>(), b6);
        Node n7 = new Node(new Point(60, 40), new Point(100, 50), new ArrayList<Node>(), b7);



    }
}

class Node{

    public ArrayList<Node> children;
    public Rectangle box;
    public Object obj;


    public Node(){
        children = new ArrayList<>();
        box = new Rectangle();
        obj = null;
    }

    public Node(Point bl, Point tr){
        children = new ArrayList<>();
        box = new Rectangle(bl, tr);
        obj = null;
    }

    public Node(Point bl, Point tr, ArrayList<Node> children){
        this.children.addAll(children);
        box = new Rectangle(bl, tr);
        obj = null;
    }

    public Node(Point bl, Point tr, ArrayList<Node> children, Object obj){
        this.children.addAll(children);
        box = new Rectangle(bl, tr);
        this.obj = obj;
    }

    public boolean containsPoint(Point p){
        return box.containsPoint(p);
    }

    public boolean boundedIn(Rectangle bx){
        return box.boundedIn(bx);
    }

    public ArrayList<Object> lookup(Point p, Node n, ArrayList<Object> results){
        if (n==this){
            for (Node child:children){
                if (child.containsPoint(p))
                    lookup(p, child, results);
            }
        } else {
            if (containsPoint(p))
                results.add(obj);
        }
        return results;
    }

    public void setMBB(Rectangle bx){
        box = new Rectangle(bx.bottom_left, bx.top_right);
    }

    public void setObj(Object obj){this.obj = obj;}

    public insert(Rectangle r, Object obj, Node n){
        if (n == this){
            for(Node child:children){
                if child.boundedIn(r)
                    child.insert(r, obj, child);
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


class Point{
    public int x, y;

    Point(){
        x = 0;
        y = 0;
    }

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Rectangle{
    public Point bottom_left, top_right;

    Rectangle(){
        bottom_left = new Point(0, 0);
        top_right = new Point(0, 0);
    }

    Rectangle(Point bl, Point ur){
        this.bottom_left = bl;
        this.top_right = ur;
    }

    public boolean containsPoint(Point p){
        return ( (p.x <= top_right.x && bottom_left.x <= p.x) && (p.y <= top_right.y && bottom_left.y <= p.y) );
    }

    public boolean boundedIn(Rectangle r){
        return ( containsPoint(r.bottom_left) && containsPoint(r.top_right) );
    }

    public boolean overlapsRect(Rectangle r){
        Point tl = new Point(r.bottom_left.x, r.top_right.y);
        Point br = new Point(r.top_right.x, r.bottom_left.y);
        return (containsPoint(r.bottom_left) || containsPoint(r.top_right) || containsPoint(ul) || containsPoint(br));
    }
}
