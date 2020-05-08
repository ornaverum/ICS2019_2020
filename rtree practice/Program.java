import java.util.ArrayList;

public class Program{
    public static void main(String[] args){

        Building b1 = new Building("School", new Rectangle(new Point(20, 60), new Point(40, 80)));
        Building b2 = new Building("Pop", new Rectangle(new Point(70, 70), new Point(80, 80)));
        Building b3 = new Building("House2", new Rectangle(new Point(70, 40), new Point(90, 60)));
        Building b4 = new Building("Road 1", new Rectangle(new Point(0, 40), new Point(60, 50)));
        Building b5 = new Building("Road 2", new Rectangle(new Point(50, 0), new Point(60, 40)));
        Building b6 = new Building("House 2", new Rectangle(new Point(20, 20), new Point(40, 40)));
        Building b7 = new Building("Pipe line", new Rectangle(new Point(60, 40), new Point(100, 50)));
        Node n1 = new Node(b1.mbb.clone(), b1);
        Node n2 = new Node(b2.mbb.clone(), b2);
        Node n3 = new Node(b3.mbb.clone(), b3);
        Node n4 = new Node(b4.mbb.clone(), b4);
        Node n5 = new Node(b5.mbb.clone(), b5);
        Node n6 = new Node(b6.mbb.clone(), b6);
        Node n7 = new Node(b7.mbb.clone(), b7);
        // Node n1 = new Node(b1.mbb.clone(), new ArrayList<Node>(), b1);
        // Node n2 = new Node(b2.mbb.clone(), new ArrayList<Node>(), b2);
        // Node n3 = new Node(b3.mbb.clone(), new ArrayList<Node>(), b3);
        // Node n4 = new Node(b4.mbb.clone(), new ArrayList<Node>(), b4);
        // Node n5 = new Node(b5.mbb.clone(), new ArrayList<Node>(), b5);
        // Node n6 = new Node(b6.mbb.clone(), new ArrayList<Node>(), b6);
        // Node n7 = new Node(b7.mbb.clone(), new ArrayList<Node>(), b7);

        // System.out.printf("%s\n", b3);

        Point p1 = new Point(95, 55);
        Point p2 = new Point(100, 65);
        Rectangle r = new Rectangle(p1, p2);
        if (b3.mbb.overlapsRect(r))
          System.out.printf("%s overlaps %s\n", b3, r);
        else
          System.out.printf("%s does not overlap %s\n", b3, r);

    }
}

class Node{

    public static int MAX_NODES = 4;

    public boolean isLeaf;
    public ArrayList<Node> children;
    public Rectangle box;
    public ArrayList<Node> contents;


    public Node(){
        children = new ArrayList<>();
        isLeaf = false;
        box = new Rectangle();
        contents = new ArrayList<>();
    }

    public Node(Rectangle r){
        children = new ArrayList<>();
        isLeaf = false;
        box = r.clone();
        contents = new ArrayList<>();
    }

    public Node(Rectangle r, ArrayList<Node> children){
        this.children = new ArrayList<>();
        this.children.addAll(children);
        box = r.clone();
        isLeaf = false;
        contents = new ArrayList<>();
    }

    public Node(Rectangle r, Object obj){
        this.children = new ArrayList<>();
        isLeaf = true;
        box = r.clone();
        contents = new ArrayList<>();
        contents.add(obj);
    }

    public Node(Rectangle r, ArrayList<Node> children){
        this.children = new ArrayList<>();
        this.children.addAll(children);
        box = r.clone();
        isLeaf = false;
        contents = new ArrayList<>();
    }

    public Node(Rectangle r, ArrayList<Node> children, ArrayList<Node> contents){
        this.children = new ArrayList<>();
        this.children.addAll(children);
        box = r.clone();
        isLeaf = true;
        this.contents = new ArrayList<>();
        this.contents.addAll(contents);
    }

    public boolean containsPoint(Point p){
        return box.containsPoint(p);
    }

    public boolean bounds(Rectangle bx){
        return box.bounds(bx);
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

    public void setMBB(Rectangle r){
        box = r.clone();
    }

    public void setObj(Object obj){this.obj = obj;}

    public void insert(Rectangle r, Object obj, Node n){
        if (n == this){
            for(Node child:children){
                //if (child.boundedIn(r))
                if (child.bounds(r))
                    child.insert(r, obj, child);
            }
        }
    }

}

class Building{
    public String name;
    public Rectangle mbb;

    Building(){
        name = "";
        mbb = new Rectangle(new Point(0,0), new Point(0,0));
    }

    Building(String name){
        this.name = name;
        mbb = new Rectangle(new Point(0,0), new Point(0,0));
    }

    Building(String name, Rectangle r){
        this.name = name;
        mbb = r.clone();
    }

    public String toString(){
        return String.format("Building %s in mbb %s.", name, mbb);
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

    public String toString(){
        return String.format("(%d, %d)", x, y);
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

    public boolean bounds(Rectangle r){
        return ( containsPoint(r.bottom_left) && containsPoint(r.top_right) );
    }

    public boolean overlapsRect(Rectangle r){
        Point tl = new Point(r.bottom_left.x, r.top_right.y);
        Point br = new Point(r.top_right.x, r.bottom_left.y);
        return (containsPoint(r.bottom_left) || containsPoint(r.top_right) || containsPoint(tl) || containsPoint(br));
    }

    public Rectangle clone(){
        return new Rectangle(this.bottom_left, this.top_right);
    }

    public String toString(){
        return String.format("[%s - %s]", bottom_left, top_right);
    }
}
