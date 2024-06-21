public class LL{
    IntNode front;
    int size;

    public LL(){
        front = null;
    }

    public LL(IntNode d, int x){
        front = d;
        size = x;
    }

    void addToFront (int newDataItem) {
        
        IntNode newNode = new IntNode(newDataItem, null);
        newNode.next = front;
        front = newNode;
        size +=1;
    }

    void addToBack (int newDataItem){
        IntNode newNode = new IntNode(newDataItem, null);
        IntNode ptr = front;
        if(ptr == null){
            front = newNode;
        }
        else if(ptr.getNextIntNode() == null){
            ptr.setNextIntNode(newNode);
        }
        else{
            while(ptr.getNextIntNode() != null){
                ptr = ptr.getNextIntNode();
            }
            ptr.setNextIntNode(newNode);
        }
        size +=1;

    }
        
    boolean search (int i) {
        
        IntNode ptr = front;
        while ( ptr != null && ptr.getInt() != i) { 
            ptr = ptr.next;
        }
       
        if ( ptr == null ) {
            return false;
        } 
        else {
            return true;
        }
    }
    
    void print () {
        IntNode ptr = front;
        while ( ptr != null ) {
            System.out.print(ptr.getInt());
            System.out.print(" ");
            ptr = ptr.getNextIntNode();
        }
        System.out.println();
    }
    

}
