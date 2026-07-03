import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Declare parallel arrays (Max capacity of 10)
        int[] studentID = new int[10];
        String[] names = new String[10];
        int[] ages = new int[10];
        String[] courses = new String[10];
        double[] grades = new double[10];
        boolean[] enrollmentStatus = new boolean[10];

        int studentCount = 0;
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n===== STUDENT INFORMATION SYSTEM =====");
            System.out.println("[1] Add Student");
            System.out.println("[2] View All Students");
            System.out.println("[3] Search Student by ID");
            System.out.println("[4] View Statistics");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: "); // Changed to print so input is on the same line

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline buffer

            switch (choice) {
                case 1:
                    // 1. Capacity Check
                    if (studentCount >= 10) {
                        System.out.println(">> Error: Student list is currently at full capacity.");
                        break; // Stop and return to main menu
                    }

                    System.out.println("\n--- Fill up student's information ---");

                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Fixed typo: capitalized the 'L' to nextLine()

                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    // 2. Age Validation
                    if (age <= 0) {
                        System.out.println(">> Error: Age must be a positive number. Student not added.");
                        break; // Stop execution, don't save data
                    }

                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine();

                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine();

                    // 3. Grade Validation
                    if (grade < 0 || grade > 100) {
                        System.out.println(">> Error: Please input a proper grade (0-100). Student not added.");
                        break; // Stop execution, don't save data
                    }

                    System.out.print("Is Enrolled (true/false): ");
                    boolean enrolled = scanner.nextBoolean();
                    scanner.nextLine();

                    // Save data into arrays if all validations passed
                    studentID[studentCount] = id;
                    names[studentCount] = name;
                    ages[studentCount] = age;
                    courses[studentCount] = course;
                    grades[studentCount] = grade;
                    enrollmentStatus[studentCount] = enrolled;

                    studentCount++;
                    System.out.println(">> Student added successfully!");
                    break;

                case 2:
                    System.out.println("\n===== STUDENT RECORDS =====");
                    if (studentCount == 0) {
                        System.out.println("There are currently no students recorded on the list.");
                    } else {
                        for (int i = 0; i < studentCount; i++) {
                            String standing;
                            if (grades[i] >= 90) {
                                standing = "Dean's Lister";
                            } else if (grades[i] >= 75) {
                                standing = "Passed";
                            } else {
                                standing = "Failed";
                            }
                            System.out.println("ID: " + studentID[i] + " | Name: " + names[i] + " | Course: " + courses[i] + " | Grade: " + grades[i] + " | Standing: " + standing);
                        }
                    }
                    break;

                case 3:
                    System.out.print("\nEnter Student ID to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine(); // Clear newline

                    boolean found = false;

                    for (int i = 0; i < studentCount; i++) {
                        if (studentID[i] == searchId) {
                            System.out.println("\n>> Student Found:");
                            System.out.println("Name: " + names[i]);
                            System.out.println("Age: " + ages[i]);
                            System.out.println("Course: " + courses[i]);
                            System.out.println("Grade: " + grades[i]);
                            System.out.println("Enrolled: " + enrollmentStatus[i]);
                            found = true;
                            break; // Stop looping once we find the matching student
                        }
                    }
                    if (!found) {
                        System.out.println(">> Error: Student ID not found.");
                    }
                    break;

                case 4:
                    System.out.println("\n===== CLASS STATISTICS =====");
                    if (studentCount == 0) {
                        System.out.println("No data available to calculate statistics.");
                    } else {
                        double totalGrades = 0;
                        double topGrade = -1;
                        String topStudent = "";

                        for (int i = 0; i < studentCount; i++) {
                            totalGrades += grades[i];

                            if (grades[i] > topGrade) {
                                topGrade = grades[i];
                                topStudent = names[i];
                            }
                        }

                        double average = totalGrades / studentCount;

                        System.out.println("Total Students: " + studentCount);
                        System.out.println("Class Average: " + average);
                        System.out.println("Top Student: " + topStudent + " (Grade: " + topGrade + ")");
                    }
                    break;

                case 5:
                    System.out.println("\nThank you for using the Student Information System. Goodbye!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close(); // Clean resource close
    }
}