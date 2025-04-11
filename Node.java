
public class Node{
    Node next,prev;
    int info;
    LinkedList list;

    Node(){
        next = prev = null;
    }
    Node(int info){
        this.info = info;
        next = null;
    }
    Node(int info,Node next){
        this.info = info;
        this.next = next;
        prev = null;
    }
    Node(int info,Node next,Node prev){
        this.info = info;
        this.next = next;
        this.prev = prev;
    }




}


