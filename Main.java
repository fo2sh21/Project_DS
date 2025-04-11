public class Main {
    public static void main(String[] args){
        SparseTable table = new SparseTable();
        table.Courses.addCourse(2);
        table.Courses.addCourse(20);
        table.Students.addStudent(3);
        table.Students.addStudent(30);
        table.enrollStudent(3,2);
        table.Students.display(3);
    }
}