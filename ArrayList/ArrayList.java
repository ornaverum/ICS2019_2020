public class ArrayList implements ICSList
{
    //instance variable
    int size;
    int[] list;

    //constructor
    public ArrayList()
    {
        size=0;
        list=new int[10];
    }

    public ArrayList(int capacity)
    {
        size=0;
        list=new int[capacity];
    }


    //methods/functions
    //add
    //delete
    //get

    private void ensureCapacity()
    {
        if(size>=list.length)
        {
            int[] newList=new int[list.length*2];
            for(int i=0;i<size;i++) newList[i]=list[i];
            list=newList;

        }
    }

    public void add(int value)
    {
        ensureCapacity();
        list[size]=value;
        size++;
    }

    public int get(int index)
    {
        if(index>=size)throw new ArrayIndexOutOfBoundsException();
        return list[index];
    }

    public int size()
    {
        return size;
    }

    public String toString()
    {
        String s="";
        for(int i=0;i<size;i++)
        {
            s+=list[i];
            if(i<size-1)s+=", ";
        }
        return "["+s+"]";
    }

    public void clear()
    {
        size=0;
    }

    /**
     * Changes value at specified index.
     *
     * @param index the index of the element to be changed
     * @param value the new element value
     * @return the old value that got replaced
     * @throws IndexOutOfBoundsException if index<0 || index>=size
     */
    public int set(int index, int value)
    {
        if(index<0 || index>=size) throw new IndexOutOfBoundsException();
        int prev=list[index];
        list[index]=value;
        return prev;

    }

    public void add(int index, int value){
      //throw new UnsupportedOperationException();
      if (index <= list.length){
        ensureCapacity();
        size++;
        int[] newList=new int[list.length];
        for(int i=0;i<index;i++) newList[i]=list[i];
        newList[index] = value;
        for(int i=index;i<size;i++) newList[i+1]=list[i];
        list = newList;
      } else {
        throw new IndexOutOfBoundsException();
      }

    }

    public int indexOf(int value){

      int i = 0;

      while (i < size && list[i]!=value){
        
      }
      throw new UnsupportedOperationException();
    }
    public ICSList subList(int fromIndex, int toIndex){throw new UnsupportedOperationException();}
    public boolean contains(int value){throw new UnsupportedOperationException();}
    public boolean	equals(ICSList list){throw new UnsupportedOperationException();}
    public boolean isEmpty(){throw new UnsupportedOperationException();}
    public int delete(int index){throw new UnsupportedOperationException();}
    public boolean	remove(int value){throw new UnsupportedOperationException();}
    public int[] toArray(){throw new UnsupportedOperationException();}
}
