/** 
 * Define the ADT List
 *
 * An interface describes behavior, "from the outside."
 */

public interface DSList<E>{
    public void add(E n); // operate by side effects
    
    public void replace(int idx, E newValue);
    
    public int length();
    
    public void sort();
    
    public void remove(int idx);
    
    public int count(E item);
    
    public E get(int idx);
    
    public int find(E item);
	

}
