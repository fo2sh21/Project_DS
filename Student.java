public class Student implements ColumnNode {
    int studentId;
    int freshId;
    Column<Course> content;

    Student(int studentId, int id) {
        this.studentId = studentId;
        this.freshId = id;
        content = new Column<Course>();
    }

    @Override
    public int getId() {
        return studentId;
    }

    @Override
    public int getFreshId() {
        return freshId;
    }

    @Override
    public void removeFromContent(int courseId) {
        content.removeById(courseId);
    }

    public boolean isNormal() {
        return content.size >= 2 && content.size <= 7;
    }

    @Override
    public String toString() {
        return String.valueOf(studentId);
    }
}
