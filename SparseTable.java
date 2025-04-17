public class SparseTable {
    LinkedList studentsList;
    LinkedList coursesList;
    
    static int FRESH_COUNT = 0;
    public SparseTable(){
        studentsList = new LinkedList();
        coursesList = new LinkedList();
    }
    public void addStudent(int studentID) {
        if (studentsList.find(studentID) != null) {
            System.out.println("Student already in table !");
            return;
        }
        studentsList.addToTail(studentID , FRESH_COUNT);
        FRESH_COUNT++;
    }

    public void addCourse(int courseID) {
        if (coursesList.find(courseID) == null) {
            coursesList.addToTail(courseID , FRESH_COUNT);
            FRESH_COUNT++;
        } else {
            System.out.println("Course already in table !");
        }
    }

    public void removeStudent(int studentID) {
        if (studentsList.remove(studentID) == 0) {
            System.out.println("Succesufully removed student !");
            coursesList.removeAllContentInstancesOfValue(studentID);
        } else {
            System.out.println("Student Not Found !");
        }
    }

    public void removeCourse(int courseID) {
        if (coursesList.remove(courseID) == 0) {
            System.out.println("Succesufully removed course !");
            studentsList.removeAllContentInstancesOfValue(courseID);
        } else {
            System.out.println("Course Not Found !");
        }
    }

    public Node getLastStudentAdded() {
        return (Node) studentsList.nodeWithMaxFreshId();
    }

    public Node getLastCourseAdded() {
        return (Node) coursesList.nodeWithMaxFreshId();
    }

    public void enrollStudent(int studentID, int courseID) {
        Node course = coursesList.find(courseID);
        Node student = studentsList.find(studentID);
        if(course == null){
            System.out.println("Course not found");
            return;
        }
        if(student == null){
            System.out.println("Student not found");
            return;
        }
        if(isFullCourse(courseID)){
            System.out.println("Course is full !");
            return;
        }
        course.content.addToTail(student);
        student.content.addToTail(course);
    }

    public void listCoursesByStudent(int studentID) {
        Node student = studentsList.find(studentID);
        if(student == null){
            System.out.println("No such student");
            return;
        }else{
            System.out.println("Courses for Student : " + studentID);
            student.content.display("Course ID : ");
        }
    }

    public void listStudentsByCourse(int courseID) {
        Node course = coursesList.find(courseID);
        if(course == null){
            System.out.println("No such course");
            return;
        }else{
            System.out.println("Students in Course : " + courseID);
            course.content.display("Student ID : ");
        }
    }

    public void sortStudentsByID() {
        studentsList.sortAscendingly();
    }

    public void sortCoursesById() {
        coursesList.sortAscendingly();
    }

    public boolean isFullCourse(int courseID) {
        Node course = coursesList.find(courseID);
        if (course != null) {
            return course.content.size >= 30;
        }
        System.out.println("Course Not Found !");
        return false;
    }

    public boolean isNormalStudent(int studentID) {
        Node student = studentsList.find(studentID);
        if (student != null) {
            return student.content.size >= 2 && student.content.size <= 7;
        }
        System.out.println("Student Not Found !");
        return false;
    }
}
