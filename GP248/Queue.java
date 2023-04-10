import java.util.LinkedList;
public class Queue{
    LinkedList <TailoringService> list;
    
    public Queue(){
        list = new LinkedList <TailoringService> ();
    }
    
    public void enqueue(TailoringService o){
        list.addLast(o);
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public int size(){
        return list.size();
    }
    
    public TailoringService dequeue(){
        return list.removeFirst();
    }
    
    public TailoringService front(){
        return list.getFirst();
    }
    
    public TailoringService rear(){
        return list.getLast();
    }
    
    public void print(){
        for(TailoringService obj: list){
            System.out.println("\t\t\t\t\t" + obj);
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}