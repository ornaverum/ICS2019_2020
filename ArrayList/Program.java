import java.util.*;

public class Program
{
    public static int[] append(int[] list,int value)
    {
        int[] newList=new int[list.length+1];
        for(int i=0;i<list.length;i++) newList[i]=list[i];
        newList[newList.length-1]=value;
        return newList;
    }


    public static void main(String[] args)
    {
        //~ Square s=new Square(5);
        //~ System.out.println(s.area());
        //~ System.out.println(s);

        /*
        int[] list=new int[0];
        long start=System.nanoTime();
        for(int i=0;i<10000;i++)
        {
            list=append(list,(int)(Math.random()*100));
        }
        System.out.println((System.nanoTime()-start)/1e9);
        //System.out.println( Arrays.toString(list) );

        list=new int[10000];
        start=System.nanoTime();
        for(int i=0;i<10000;i++)
        {
            list[i]=(int)(Math.random()*100);
        }
        System.out.println((System.nanoTime()-start)/1e9);
        */
        //System.out.println( Arrays.toString(list) );


        //~ ArrayList list=new ArrayList();
        //~ for(int i=0;i<1000;i++)list.add(i);
        //~ System.out.println(list.get(5));
        //~ System.out.println(list.set(987,314159));
        //~ System.out.println(list);

        //~ setTest1();
        //~ setTest2();
        //timeTest();

        ArrayList list=new ArrayList();
        int n = 10;
        for(int i=0;i<n;i++) list.add(i);

        System.out.println(list);

        list.add(11, 100);
        System.out.println(list);

        list.add(6, 500);
        System.out.println(list);

    }

    public static void timeTest()
    {

        for(int i=0;i<29;i++)
        {
            int n=(int)Math.pow(2,i);
            double time=timeAdd(n);
            System.out.println(i+","+n+","+time);
        }


    }


    public static double timeAdd(int n)
    {
        ArrayList list=new ArrayList();
        long start=System.nanoTime();
        for(int i=0;i<n;i++)list.add(i);
        return (System.nanoTime()-start)/1e9;


    }


    public static void setTest1()
    {
        ArrayList list=new ArrayList();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        int v=list.set(1,14);
        int v2=list.get(1);
        if(v==6  && v2==14 )System.out.println("SUCCESS: SET TEST 1");
        else System.out.println("FAIL: SET TEST 1");

    }

    public static void setTest2()
    {
        ArrayList list=new ArrayList();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        try
        {
            list.set(4,234);
            System.out.println("FAIL: SET TEST 2");

        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("SUCCESS: SET TEST 2");
        }
        catch(Exception e)
        {
            System.out.println("FAIL: SET TEST 2");
        }

    }

}





class Square
{
    //instance variables
    double length;

    //constructor
    public Square(double length)
    {
        this.length=length;
    }

    //methods/function
    public double area()
    {
        return length*length;
    }

    public void scale(double factor)
    {
        length*=factor;
    }

    public String toString()
    {
        return "I am a square with side length "+length+"!";
    }




}
