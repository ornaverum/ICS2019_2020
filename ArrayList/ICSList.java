//https://docs.oracle.com/javase/8/docs/api/java/util/List.html

public interface ICSList
{
    public void add(int value);
    public void add(int index, int value);
    public int indexOf(int value);
    public int set(int index, int value);
    public ICSList subList(int fromIndex, int toIndex);
    public int get(int index);
    public void clear();
    public boolean contains(int value);
    public boolean	equals(ICSList list);
    public boolean isEmpty();
    public int delete(int index);
    public boolean	remove(int value); //removes first
    public int	size();
    public int[] toArray();
}
