public class Node{
    Node next,prev;
    int info;
    LinkedList content;
    int FRESH_ID;
    
    Node(int id){
        next = prev = null;
        content = new LinkedList();
        this.FRESH_ID = id;
    }
    Node(int info , int id){
        this.info = info;
        this.FRESH_ID = id;
        next = null;
        content = new LinkedList();
    }
    Node(int info,Node next , int id){
        this.FRESH_ID = id;
        this.info = info;
        this.next = next;
        prev = null;
        content = new LinkedList();
    }
    Node(int info,Node next,Node prev , int id){
        this.FRESH_ID = id;
        this.info = info;
        this.next = next;
        this.prev = prev;
        content = new LinkedList();
    }
}


