/**
 * Generic DSArrayList that can hold objects of *any* type
 */
class DSArrayList<E> implements DSList<E> { // automatically a subclass of Object
    // therefore it inherits all methods of the Object class
    // Fields
    private E[] a;// = new int[10]; // The backing array
    private int length; // Number of items that count as being in the list
    private int capacity; // Always equal to a.length

    // Methods

    /**
     * Default constructor - builds an object of the class
     */
    @SuppressWarnings("unchecked")
    public DSArrayList(){
        this.length = 0; // Number of items that count as being in the list
        this.capacity = 10; // Always equal to a.length
        this.a = (E[])(new Object[this.capacity]); // The backing array
    }

    /**
     * The add method should never run out of space
     * To effect this, we'll check before we add a new item:
     *  - Are we about to add it beyond the end of the array?
     *  - If so, make a bigger array, copy into it, update reference a
     *       
     */
    @SuppressWarnings("unchecked")
    public void add(E n) {
        if(this.length >= this.capacity){ // need to add space
            //int newCapacity = capacity + 1000000; // Memory wasteful
            //int newCapacity = capacity + 10; // Time slowful
            int newCapacity = (int)(capacity * 1.05) + 1; // fast, unwasteful
            //int newCapacity = 2 * capacity; // industry standard
            //System.out.println("Resizing to size " + newCapacity);
            E[] newA = (E[])(new Object[newCapacity]);
            for(int i = 0; i < this.capacity; i++){
                newA[i] = a[i];
            }
            this.a = newA;
            this.capacity = newCapacity;
        }
        this.a[this.length] = n;
        this.length++;
    } // operate by side effects


    /**
     * Replace the item at index idx with the newValue
     */
    public void replace(int idx, E newValue) {
        this.a[idx] = newValue;
    }

    public int length() {
        return this.length;
    }

    public void sort() {
    }

    public void remove(int idx) { // today
        // Move the items to get rid of the item at index idx
        for (int i = idx; i < this.length - 1; i++) {
            this.a[i] = this.a[i + 1];
        }
        // Now some bookkeeping
        this.length--;
    }

    /**
     * Counts number of occurrences of item in our List,
     * assuming that item type implements the equals method.
     * 
     * @param item The item to enumerate in our list
     * @return The number of occurrences of item in our list
     */
    public int count(E item) {
        int c = 0;
        for (int i = 0; i < this.length; i++) {
            if (this.a[i].equals(item))
                c++;
        }
        return c;
    }


    public E get(int idx) {
        return this.a[idx];
    }

    /**
     * Return the index of the first occurrence of item
     * Or return -1 if it is not there.
     */
    public int find(E item) {
        for (int i = 0; i < this.length; i++) {
            if (this.a[i].equals(item))
                return i;
        }
        return -1;
    }

    // We would like our DSArrayList objects to be able to print themselves
    // And it should look pretty!
    // To do this we override the "toString()" method of the object class
    @Override
    public String toString() {
        String rv = "["; // The string we will eventually return

        if(this.length == 0) return "[]";

        for (int i = 0; i < this.length - 1; i++) {
            rv = rv + this.a[i] + ", ";
        }
        // Add the last element of the list
        rv = rv + this.a[this.length - 1] + "]";
        return rv;
    }

}
