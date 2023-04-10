public class LinkedList {
    Node head, current, tail;
    public LinkedList() { //create an empty list
        head = current = tail = null;
    }
    // Phase 2: b) i)
    public void addFront(TailoringService o) { //add object in the first node of the list
        if(isEmpty()) {
            head = tail = new Node(o);
        }
        else {
            Node node = new Node(o);
            node.next = head;
            head = node;
        }
    }

    public void addBack(TailoringService o) { //add object in the last node of the list
        if(isEmpty()) {
            head = tail = new Node(o);
        }
        else {
            Node node = new Node(o);
            tail.next = node;
            tail = tail.next;
        }
    }
    // Phase 2: b) ii)
    public TailoringService deleteFront() {
        TailoringService obj = null;
        if(isEmpty())
            throw new EmptyListException();
        else if(head == tail) {
            obj = head.data;
            head = tail = null;
        }
        else {
            obj = head.data;
            head = head.next;
        }
        return obj;
    }

    public TailoringService deleteBack() {
        TailoringService obj = null;
        if(isEmpty())
            throw new EmptyListException();
        else if(head == tail) {
            obj = head.data;
            head = tail = null;
        }
        else {
            obj = tail.data;
            Node node = head;
            while(node.next != tail)
                node = node.next;
            node.next = null;
            tail = node;
        }
        return obj;
    }

    public TailoringService remove(TailoringService o) { //remove and return an object from the list
        TailoringService remove = null;
        if(isEmpty()){
            throw new EmptyListException(); 
        }
        else if(head.data == o) { //check wether the element to be removed is the first element or not
            remove = deleteFront(); //remove the first element
        }
        else if(tail.data == o){
            remove = deleteBack(); //remove the last element
        }
        else {
            Node node = head;
            while(node.next != null) {
                if(node.next.data == o) {
                    remove = node.next.data;
                    node.next = node.next.next;
                }
                else
                    node = node.next;
            }
        }
        return remove; 
    }
    // Phase 2: b) iii)
    public TailoringService getHead() { //return the first node of the list
        if(isEmpty()) {
            return null;
        }
        else {
            current = head;
            return current.data;
        }
    }

    public TailoringService getNext() { //return the next of the current node
        if(current != tail) {
            current = current.next;
            return current.data;
        }
        else
            return null;
    }
    // Phase 2: b) iv)
    public boolean isEmpty() { //check if the list is empty
        return head == null;
    }
    // Phase 2: b) v)
    public int size() { //return the size of the list
        int size = 0;
        Node node = head;
        while(node != null) {
            size++;
            node = node.next;
        }
        return size;
    }
    // Phase 2: b) vi)
    public void print() {
        Node node = head;
        while(node != null) {
            System.out.println("\t\t\t\t\t" + node.data);
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            node = node.next;
        }
    }
}
