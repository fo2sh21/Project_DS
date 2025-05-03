import java.util.Stack;

public class SparseTable {
    Column<Student> studentsList;
    Column<Course> coursesList;
    int freshCount;

    Stack<SparseTable> undos = new Stack<>();
    Stack<SparseTable> redos = new Stack<>();

    public SparseTable() {
        studentsList = new Column<>();
        coursesList = new Column<>();
    }

    public SparseTable(SparseTable other) {
        freshCount = other.freshCount;
        this.studentsList = new Column<>();
        this.coursesList = new Column<>();

        for (Node<Student> node = other.studentsList.head; node != null; node = node.next) {
            this.studentsList.addToTail(new Student(node.info.getId(), node.info.getFreshId()));
        }

        for (Node<Course> node = other.coursesList.head; node != null; node = node.next) {
            this.coursesList.addToTail(new Course(node.info.getId(), node.info.getFreshId()));
        }

        for (Node<Student> node = other.studentsList.head,
                newNode = this.studentsList.head; node != null; node = node.next, newNode = newNode.next) {

            for (Node<Course> course = node.info.content.head; course != null; course = course.next) {
                newNode.info.content.addToTail(new Course(course.info.getId(), course.info.getFreshId()));
            }
        }

        for (Node<Course> node = other.coursesList.head,
                newNode = this.coursesList.head; node != null; node = node.next, newNode = newNode.next) {

            for (Node<Student> student = node.info.content.head; student != null; student = student.next) {
                newNode.info.content.addToTail(new Student(student.info.getId(), student.info.getFreshId()));
            }
        }
    }

    public void addStudent(int studentID) {

        if (studentsList.findById(studentID) != null) {
            System.out.println("Student already in table");
            return;
        }
        record();
        studentsList.addToTail(new Student(studentID, freshCount));
        freshCount++;
    }

    public void addCourse(int courseID) {

        if (coursesList.findById(courseID) == null) {
            record();
            coursesList.addToTail(new Course(courseID, freshCount));
            freshCount++;

        } else {
            System.out.println("Course already in table");
        }
    }

    public void removeStudent(int studentID) {
        record();
        if (studentsList.removeById(studentID) == 0) {
            System.out.println("Succesufully removed student");
            coursesList.removeAllContentInstancesOfValue(studentID);
        } else {
            System.out.println("Student Not Found");
        }
    }

    public void removeCourse(int courseID) {
        record();
        if (coursesList.removeById(courseID) == 0) {
            System.out.println("Succesufully removed course");
            studentsList.removeAllContentInstancesOfValue(courseID);
        } else {
            System.out.println("Course Not Found");
        }
    }

    public Student getLastStudentAdded() {
        Node<Student> node = studentsList.nodeWithMaxFreshId();
        return node != null ? node.info : null;
    }

    public Course getLastCourseAdded() {
        Node<Course> node = coursesList.nodeWithMaxFreshId();
        return node != null ? node.info : null;
    }

    public void enrollStudent(int studentID, int courseID) {
        Node<Course> course = coursesList.findById(courseID);
        Node<Student> student = studentsList.findById(studentID);
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
        record();
        course.info.content.addToTail(new Student(student.info.studentId, student.info.freshId));
        student.info.content.addToTail(new Course(course.info.courseId, course.info.freshId));
    }

    public void removeEnrollment(int studentID, int courseID) {
        Node<Course> course = coursesList.findById(courseID);
        Node<Student> student = studentsList.findById(studentID);
        if (course == null) {
            System.out.println("Course not found");
            return;
        }
        if (student == null) {
            System.out.println("Student not found");
            return;
        }
        if (student.info.content.findById(course.info.getId()) == null) {
            System.out.println("Student not enrolled to the course");
            return;
        }
        record();
        course.info.content.removeById(studentID);
        student.info.content.removeById(courseID);
    }

    public void listCoursesByStudent(int studentID) {
        Node<Student> student = studentsList.findById(studentID);
        if (student == null) {
            System.out.println("No such student");
            return;
        } else {
            System.out.println("Courses for Student(" + studentID + ")");
            student.info.content.display("   Course ID: ");
        }
    }

    public void listStudentsByCourse(int courseID) {
        Node<Course> course = coursesList.findById(courseID);
        if (course == null) {
            System.out.println("No such course");
            return;
        } else {
            System.out.println("Students in Course(" + courseID + "):");
            course.info.content.display("    Student ID: ");
        }
    }

    public void sortStudentsByID(int courseId) {
        Node<Course> course = coursesList.findById(courseId);
        if (course == null) {
            System.out.println("No such course");
            return;
        }
        record();
        course.info.content.sortAscendingly();
    }

    public void sortCoursesById(int studentId) {
        Node<Student> student = studentsList.findById(studentId);
        if (student == null) {
            System.out.println("No such student");
            return;
        }
        record();
        student.info.content.sortAscendingly();
    }

    public boolean isFullCourse(int courseID) {
        Node<Course> course = coursesList.findById(courseID);
        if (course != null) {
            return course.info.isFull();
        }
        System.out.println("Course Not Found");
        return false;
    }

    public boolean isNormalStudent(int studentID) {
        Node<Student> student = studentsList.findById(studentID);
        if (student != null) {
            return student.info.isNormal();
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
