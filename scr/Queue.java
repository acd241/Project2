public class Queue {
    private IntNode first; 
    private IntNode last; 
    private int size;

    
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return first == null;
    }
   
    public void enqueue(int item) {
        IntNode oldLast = last; 
        last = new IntNode(item, null); 
        if ( isEmpty() ) {
            first = last;
        } 
        else {
            oldLast.next = last;
        }
        size += 1;
    }
    
    public int dequeue() {
        IntNode item = first; 
        first = first.next; 
        if (isEmpty()){
            last = null; 
        }  
        size -= 1;
        return item.getInt();
    }
}
