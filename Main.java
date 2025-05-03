public class Main {
    public static void main(String[] args) {
        SparseTable table = new SparseTable();

        // 1) Add students and courses
        System.out.println("=== Adding students and courses ===");
        table.addStudent(1);
        table.addStudent(2);
        table.addCourse(101);
        table.addCourse(102);
        System.out.println();

        // 2) Enroll student 1 in course 101
        System.out.println("=== Enrolling student 1 in course 101 ===");
        table.enrollStudent(1, 101);
        table.listStudentsByCourse(101);
        table.listCoursesByStudent(1);
        System.out.println();

        // 3) Undo the enrollment
        System.out.println("=== Undo enrollment ===");
        table.undo();
        table.listStudentsByCourse(101);
        table.listCoursesByStudent(1);
        System.out.println();

        // 4) Redo the enrollment
        System.out.println("=== Redo enrollment ===");
        table.redo();
        table.listStudentsByCourse(101);
        table.listCoursesByStudent(1);
        System.out.println();

        // 5) Remove student 1 (this also removes any enrollments)
        System.out.println("=== Removing student 1 ===");
        table.removeStudent(1);
        table.listStudentsByCourse(101);
        table.listCoursesByStudent(1);
        System.out.println();

        // 6) Undo the removal
        System.out.println("=== Undo removal of student 1 ===");
        table.undo();
        table.listStudentsByCourse(101);
        table.listCoursesByStudent(1);
        System.out.println();

        // 7) Final state: list everything explicitly
        System.out.println("=== Final state ===");
        System.out.println("Last student added: " +
                table.getLastStudentAdded().studentId);
        System.out.println("Last course added: " +
                table.getLastCourseAdded().courseId);
    }
}
