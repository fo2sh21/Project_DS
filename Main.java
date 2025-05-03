public class Main {
    public static void main(String[] args) {
        SparseTable table = new SparseTable();

        System.out.println("=== Adding Students ===");
        table.addStudent(1);
        table.addStudent(2);
        table.addStudent(3);
        table.addStudent(2); // duplicate

        System.out.println("\n=== Adding Courses ===");
        table.addCourse(101);
        table.addCourse(102);
        table.addCourse(103);
        table.addCourse(100);
        table.addCourse(101); // duplicate

        System.out.println("\n=== Last Added ===");
        System.out.println("Last student: " + table.getLastStudentAdded());
        System.out.println("Last course:  " + table.getLastCourseAdded());

        System.out.println("\n=== Enrollments ===");
        table.enrollStudent(1, 101);
        table.enrollStudent(1, 102);
        table.enrollStudent(3, 101);
        table.enrollStudent(4, 101); // student not found
        table.enrollStudent(1, 999); // course not found

        System.out.println("\n=== List after enrollment ===");
        table.listCoursesByStudent(1);
        table.listStudentsByCourse(101);

        System.out.println("\n=== Remove Enrollment ===");
        table.removeEnrollment(1, 102);
        table.listCoursesByStudent(1);

        System.out.println("\n=== Sort Courses for Student 1 ===");
        // add out‑of‑order courses
        table.enrollStudent(1, 102);
        table.enrollStudent(1, 103);
        table.enrollStudent(1, 100);
        table.listCoursesByStudent(1);
        table.sortCoursesById(1);
        table.listCoursesByStudent(1);

        System.out.println("\n=== Sort Students in Course 101 ===");
        // add out‑of‑order students
        table.enrollStudent(2, 101);
        table.enrollStudent(4, 101); // not found
        table.listStudentsByCourse(101);
        table.sortStudentsByID(101);
        table.listStudentsByCourse(101);

        System.out.println("\n=== Status Checks ===");
        System.out.println("Is course 101 full?  " + table.isFullCourse(101));
        System.out.println("Is student 1 normal? " + table.isNormalStudent(1));
        System.out.println("Is student 9 normal? " + table.isNormalStudent(9)); // not found

        System.out.println("\n=== Remove Student 3 and Undo/Redo ===");
        table.removeStudent(3);
        table.listStudentsByCourse(101);
        table.undo();
        System.out.println("After undo:");
        table.listStudentsByCourse(101);
        table.redo();
        System.out.println("After redo:");
        table.listStudentsByCourse(101);

        System.out.println("\n=== Remove Course 102 and Undo/Redo ===");
        table.removeCourse(102);
        table.listCoursesByStudent(1);
        table.undo();
        System.out.println("After undo:");
        table.listCoursesByStudent(1);
        table.redo();
        System.out.println("After redo:");
        table.listCoursesByStudent(1);
    }
}
