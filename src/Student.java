public class Student {
    private String studentId;
    private String fullName;
    private String program;
    private int yearLevel;


    // Constructor: initialize all fields with 'this' keyword
    public Student(String studentId, String fullName, String program, int yearLevel) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.program = program;
        this.yearLevel = yearLevel;
    }


    // TODO: Getters (fullName, program, yearLevel)
    public String getStudentId() {
        return studentId;
    }
    //TODO starts here
    public String getFullName() {
        return fullName;
    }


    public String getProgram() {
        return program;
    }


    public int getYearLevel() {
        return yearLevel;
    }
    //TODO ends here

    // Returns a one-line summary of the student
    public String describe() {
        return studentId + " | " + fullName + " | " + program + " | Year " + yearLevel;
    }
}
