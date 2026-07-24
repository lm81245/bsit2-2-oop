public class Student {
    private String studentId;
    private String fullName;
    private String program;
    private int yearLevel;


    // Constructor: initialize all fields with 'this'
    public Student(String studentId, String fullName, String program, int yearLevel) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.program = program;
        this.yearLevel = yearLevel;
    }


    // Getters (The first one was provided, the next three are the missing ones)
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
public class Course {
    private String courseCode;
    private String title;
    private int units;
    private int capacity;
    private int enrolledCount;


    public Course(String courseCode, String title,
                  int units, int capacity) {
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


    // TODO starts here


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



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class EnrollmentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<String, ArrayList<String>> enrollments =
            new HashMap<>();
        String[] validPrograms = {"BSIT", "BSCS"};


        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = Integer.parseInt(sc.nextLine());


            //ENTIRETY OF THE SWITCH CODE BLOCK BELOW IS TODO
            switch (choice) {
                case 1: // Register Student
                    System.out.print("Student ID : ");
                    String id = sc.nextLine();
                    System.out.print("Full Name : ");
                    String name = sc.nextLine();
                    System.out.print("Program : ");
                    String program = sc.nextLine();
                    System.out.print("Year Level : ");
                    int yearLevel = Integer.parseInt(sc.nextLine());


                    boolean isValid = false;
                    for (String p : validPrograms) {
                        if (p.equals(program)) {
                            isValid = true;
                            break;
                        }
                    }
                    if (!isValid || yearLevel < 1 || yearLevel > 4) {
                        System.out.println("[ERROR] Invalid program or year level.");
                        break;
                    }


                    students.add(new Student(id, name, program, yearLevel));
                    System.out.println("[OK] Registered!");
                    break;


                case 2: // Add Course Offering
                    System.out.print("Course Code : ");
                    String courseCode = sc.nextLine();
                    System.out.print("Title : ");
                    String title = sc.nextLine();
                    System.out.print("Units : ");
                    int units = Integer.parseInt(sc.nextLine());
                    System.out.print("Capacity : ");
                    int capacity = Integer.parseInt(sc.nextLine());


                    courses.add(new Course(courseCode, title, units, capacity));
                    System.out.println("[OK] Course added!");
                    break;


                case 3: // Enroll Student to Course
                    System.out.print("Student ID : ");
                    String enrollId = sc.nextLine();
                    System.out.print("Course Code : ");
                    String enrollCode = sc.nextLine();


                    Student s = findStudent(students, enrollId);
                    Course c = findCourse(courses, enrollCode);


                    // Strict Validation: exists, isFull, already enrolled
                    if (s == null) {
                        System.out.println("[ERROR] Student not found.");
                        break;
                    }
                    if (c == null) {
                        System.out.println("[ERROR] Course not found.");
                        break;
                    }
                    if (c.isFull()) {
                        System.out.println("[ERROR] Course is full.");
                        break;
                    }
                   
                    enrollments.putIfAbsent(enrollId, new ArrayList<>());
                    if (enrollments.get(enrollId).contains(enrollCode)) {
                        System.out.println("[ERROR] Already enrolled in this course.");
                        break;
                    }


                    enrollments.get(enrollId).add(enrollCode);
                    c.addOneEnrollee();
                    System.out.println("[OK] Enrolled!");
                    break;


                case 4: // View All Students
                    if (students.isEmpty()) {
                        System.out.println("No students yet.");
                    } else {
                        for (Student student : students) {
                            System.out.println(student.describe());
                        }
                    }
                    break;


                case 5: // View All Courses
                    for (Course course : courses) {
                        System.out.println(course.getCourseCode() + " | " + course.getTitle() +
                                           " | " + course.getUnits() + " units | " +
                                           course.getEnrolledCount() + "/" + course.getCapacity());
                    }
                    break;


                case 6: // View Student Load
                    System.out.print("Student ID : ");
                    String loadId = sc.nextLine();
                   
                    ArrayList<String> studentCourses = enrollments.get(loadId);
                    int totalUnits = 0;
                   
                    if (studentCourses != null) {
                        for (String code : studentCourses) {
                            Course enrolledCourse = findCourse(courses, code);
                            if (enrolledCourse != null) {
                                System.out.println(enrolledCourse.getCourseCode() + " " + enrolledCourse.getUnits() + " units");
                                totalUnits += enrolledCourse.getUnits();
                            }
                        }
                    }
                    System.out.println("Total Units: " + totalUnits);
                    break;


                case 0:
                    System.out.println("Thank you!");
                    break;


                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    static void printMenu() { //SYSTEM OUTPUT IS GIVEN
        System.out.println("========================================");
        System.out.println("LICEO ENROLLMENT SYSTEM (CLI)");
        System.out.println("========================================");
        System.out.println("[1] Register Student");
        System.out.println("[2] Add Course Offering");
        System.out.println("[3] Enroll Student to Course");
        System.out.println("[4] View All Students");
        System.out.println("[5] View All Courses");
        System.out.println("[6] View Student Load (Courses + Total Units)");
        System.out.println("[0] Exit");
        System.out.println("----------------------------------------");
        System.out.print("Enter choice: ");
    }


    //ENTIRE CODE BLOCK IS GIVEN
    static Student findStudent(ArrayList<Student> list, String id) {
        for (Student s : list) {
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }
        return null; //not found
    }


    //ENTIRE CODE BLOCK CAN BE DUPLICATED BASE OFF PREVIOUS BLOCK
    static Course findCourse(ArrayList<Course> list, String code) {
        for (Course c : list) {
            if (c.getCourseCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}



