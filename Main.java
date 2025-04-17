public class Main {
    public static void main(String[] args){
        SparseTable table = new SparseTable();
        table.addStudent(1);
        table.addStudent(5);
        table.addStudent(3);
        table.addCourse(1);
        table.addCourse(2);
        table.addCourse(6);
        table.enrollStudent(1, 1);
        table.enrollStudent(5, 1);
        table.enrollStudent(3, 1);
        table.enrollStudent(1, 2);
        table.enrollStudent(5, 2);
        table.enrollStudent(3, 2);
        table.enrollStudent(1, 6);
        table.enrollStudent(5, 6);
        table.enrollStudent(3, 6);
        table.listStudentsByCourse(1);
        table.sortStudentsByID();
        table.sortCoursesById();
        table.listStudentsByCourse(1);
        table.listCoursesByStudent(1);
        table.listStudentsByCourse(2);
        table.listCoursesByStudent(3);

    }
}
