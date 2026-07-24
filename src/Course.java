public class Course {
    private String courseCode;
    private String title;
    private int units;
    private int capacity;
    private int enrolledCount;


    public Course(String courseCode, String title, int units, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.units = units;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    public boolean isFull() {
        return enrolledCount >= capacity;
    }

    public void addOneEnrollee() {
        if (!isFull()) {
            enrolledCount++;
        }
    }

    // TODO: Getter (courseCode, title, units, capacity, enrolledCount)
    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getUnits() {
        return units;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }
    // TODO ends here
}
