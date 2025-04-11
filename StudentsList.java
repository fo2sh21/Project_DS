public class StudentsList extends LinkedList {
    private int lastStudentAdded;

    StudentsList() {
        super();
    }

    public int addStudent(int id) {
        Node student = find(id);
        if (student != null) return -1;
        super.addToTail(id);
        tail.list = new LinkedList();
        lastStudentAdded = id;
        return 0;
    }

    public void removeStudent(int id) {
        super.remove(id);
    }

    public int getLastStudentAdded() {
        return lastStudentAdded;
    }
    public void display(int id){
        for(Node current = head; current!=null; current = current.next){
            if (current.info == id){
                if (current.list != null){
                    System.out.println("Enrolled in:");
                    for(Node c = current.list.head; c!=null; c = c.next){
                        System.out.println("Course ID: " +c.info);
                    }
                    return;
                }
            }
        }
    }
}
