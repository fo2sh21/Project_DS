import java.util.Stack;

public class SparseTable {
    LinkedList studentsList;
    LinkedList coursesList;
    int freshCount;

    Stack<SparseTable> undos = new Stack<>();
    Stack<SparseTable> redos = new Stack<>();

    public SparseTable() {
        studentsList = new LinkedList();
        coursesList = new LinkedList();
    }

    public SparseTable(SparseTable other) {
        studentsList = new LinkedList(other.studentsList);
        coursesList = new LinkedList(other.coursesList);
        freshCount = other.freshCount;
    }

    public void addStudent(int studentID) {
        record();
        if (studentsList.find(studentID) != null) {
            System.out.println("Student already in table");
            return;
        }
        studentsList.addToTail(studentID, freshCount);
        freshCount++;
    }

    public void addCourse(int courseID) {
        record();
        if (coursesList.find(courseID) == null) {
            coursesList.addToTail(courseID, freshCount);
            freshCount++;
        } else {
            System.out.println("Course already in table");
        }
    }

    public void removeStudent(int studentID) {
        record();
        if (studentsList.remove(studentID) == 0) {
            System.out.println("Succesufully removed student");
            coursesList.removeAllContentInstancesOfValue(studentID);
        } else {
            System.out.println("Student Not Found");
        }
    }

    public void removeCourse(int courseID) {
        record();
        if (coursesList.remove(courseID) == 0) {
            System.out.println("Succesufully removed course");
            studentsList.removeAllContentInstancesOfValue(courseID);
        } else {
            System.out.println("Course Not Found");
        }
    }

    public Node getLastStudentAdded() {
        return (Node) studentsList.nodeWithMaxFreshId();
    }

    public Node getLastCourseAdded() {
        return (Node) coursesList.nodeWithMaxFreshId();
    }

    public void enrollStudent(int studentID, int courseID) {
        record();
        Node course = coursesList.find(courseID);
        Node student = studentsList.find(studentID);
        if (course == null) {
            System.out.println("Course not found");
            return;
        }
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        if (isFullCourse(courseID)) {
            System.out.println("Course is full");
            return;
        }
        Node courseReference = new Node(course.info, course.freshId);
        Node studentReference = new Node(student.info, student.freshId);
        course.content.addToTail(studentReference);
        student.content.addToTail(courseReference);
    }

    public void listCoursesByStudent(int studentID) {
        Node student = studentsList.find(studentID);
        if (student == null) {
            System.out.println("No such student");
            return;
        } else {
            System.out.println("Courses for Student(" + studentID + ")");
            student.content.display("   Course ID: ");
        }
    }

    public void listStudentsByCourse(int courseID) {
        Node course = coursesList.find(courseID);
        if (course == null) {
            System.out.println("No such course");
            return;
        } else {
            System.out.println("Students in Course(" + courseID + "):");
            course.content.display("    Student ID: ");
        }
    }

    public void sortStudentsByID() {
        record();
        studentsList.sortAscendingly();
    }

    public void sortCoursesById() {
        record();
        coursesList.sortAscendingly();
    }

    public boolean isFullCourse(int courseID) {
        Node course = coursesList.find(courseID);
        if (course != null) {
            return course.content.size >= 30;
        }
        System.out.println("Course Not Found");
        return false;
    }

    public boolean isNormalStudent(int studentID) {
        Node student = studentsList.find(studentID);
        if (student != null) {
            return student.content.size >= 2 && student.content.size <= 7;
        }
        System.out.println("Student Not Found");
        return false;
    }

    private void record() {
        undos.push(new SparseTable(this));
        redos.clear();
    }

    private void restore(SparseTable other) {
        this.freshCount = other.freshCount;
        this.studentsList = other.studentsList;
        this.coursesList = other.coursesList;
    }

    public void undo() {
        if (undos.isEmpty())
            return;

        redos.push(new SparseTable(this));
        restore(undos.pop());
    }

    public void redo() {
        if (redos.isEmpty())
            return;

        undos.push(new SparseTable(this));
        restore(redos.pop());
    }
}
