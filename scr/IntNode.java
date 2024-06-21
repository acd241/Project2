public class IntNode {
    int p;
    IntNode next; // link part of the node
    

    public IntNode (int x, IntNode c){
        this.p = x;
        this.next = c;
    }

    public int getInt() { return p; }
    public void setInt(int x ) { this.p = x; }

    public IntNode getNextIntNode() { return next; }
    public void setNextIntNode(IntNode ne) { this.next = ne; }

}