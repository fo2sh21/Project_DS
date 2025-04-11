public class SparseTable {
    StudentsList Students;
    CoursesList Courses;

    SparseTable(){
        Students = new StudentsList();
        Courses = new CoursesList();
    }

    public int enrollStudent(int s,int c){
        Node student = Students.find(s);
        Node course = Courses.find(c);
        if (student != null && course != null){
            Node check_course = student.list.find(c);
            Node check_student = course.list.find(s);
            if (check_student == null && check_course == null){
                student.list.addToTail(c);
                course.list.addToTail(s);
                return 0;
            }
        }
        return -1;
    }
}


