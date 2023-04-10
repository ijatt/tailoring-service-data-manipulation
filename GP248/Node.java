public class Node {
    Node next;
    TailoringService data;
    
    public Node(){next = null; data = null;}
    public Node(TailoringService o) {
        this.data = o;
        next = null;
    }
}
