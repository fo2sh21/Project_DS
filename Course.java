public class Course implements ColumnNode {
    int courseId;
    int freshId;
    Column<Student> content;

    Course(int courseId, int id) {
        this.courseId = courseId;
        this.freshId = id;
        content = new Column<Student>();
    }

    @Override
    public int getId() {
        return courseId;
    }

    @Override
    public int getFreshId() {
        return freshId;
    }

    @Override
    public void removeFromContent(int studentId) {
        content.removeById(studentId);
    }

    public boolean isFull() {
        return content.size >= 30;
    }

    @Override
    public String toString() {
        return String.valueOf(courseId);
    }
}
