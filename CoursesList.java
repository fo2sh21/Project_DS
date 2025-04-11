public class CoursesList extends LinkedList{
    private int lastCourseAdded;

    CoursesList(){
        super();
    }
    public int addCourse(int id){
        Node course = find(id);
        if (course != null) return -1;
        super.addToTail(id);
        tail.list = new LinkedList();
        lastCourseAdded = id;
        return 0;
    }
    public void removeCourse(int id){
        super.remove(id);
    }
    public int getLastCourseAdded() {
        return lastCourseAdded;
    }
    public void display(int id){
        for(Node current = head; current!=null; current = current.next){
            if (current.info == id){
                if (current.list != null){
                    System.out.println("Enrolled students:");
                    for(Node c = current.list.head; c!=null; c = c.next){
                        System.out.println("Student ID: " + c.info);
                    }
                }
            }
        }
    }
}
