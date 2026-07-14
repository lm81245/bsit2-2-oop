import java.util.*;

public class GradeTracker {

    static double[] cutoffs = {90.0, 80.0, 70.0, 60.0};
    static String[] letters = {"A", "B", "C", "D", "F"};

    public static String letterFor(double grade) {
        for (int i = 0; i < cutoffs.length; i++) {
            if (grade >= cutoffs[i]) {
                return letters[i];
            }
        }
        return "F";
    }

        public static void main(String[] args) {

            ArrayList<Student> roster = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            boolean running = true;

            System.out.println("=====================================");
            System.out.println("   Welcome to Student Grade Tracker  ");
            System.out.println("=====================================");

            while (running) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Add student");
                System.out.println("2. View all");
                System.out.println("3. Class statistics (Average & Top Student)");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                if (sc.hasNextInt()) {
                    int choice = sc.nextInt();

                    if (choice == 1) {
                        String name = "";
                        boolean validName = false;

                        // NAME VALIDATION: Ensure it only contains letters
                        while (!validName) {
                            System.out.print("Enter student name: ");
                            name = sc.next();

                            // The regex "[a-zA-Z]+" means "one or more letters from A-Z or a-z"
                            if (name.matches("[a-zA-Z]+")) {
                                validName = true;
                            } else {
                                System.out.println("[Error] Name must contain only letters. No numbers or special characters allowed.");
                            }
                        }

                        double grade = -1;
                        boolean validGrade = false;

                        // RUBRIC REQUIREMENT: Range validation (0-100)
                        while (!validGrade) {
                            System.out.print("Enter numerical grade (0-100): ");
                            if (sc.hasNextDouble()) {
                                grade = sc.nextDouble();
                                if (grade >= 0 && grade <= 100) {
                                    validGrade = true; // Input is safe and within range
                                } else {
                                    System.out.println("[Error] Grade must be between 0 and 100.");
                                }
                            } else {
                                System.out.println("[Error] Please enter a valid number.");
                                sc.next(); // Clear bad input
                            }
                        }

                        // Create a new Student object and add it to the ArrayList
                        Student newStudent = new Student(name, grade);
                        roster.add(newStudent);
                        System.out.println("[Success] " + name + " added successfully.");

                    }
                    else if (choice == 2) {
                        System.out.println("\n--- Class Roster ---");

                        for (Student s : roster) {
                            System.out.printf("%-5s - %.1f (%s)%n", s.name, s.grade, letterFor(s.grade));
                        }
                    }
                    else if (choice == 3) {
                        // 1. Check if the list is empty to prevent a divide-by-zero (NaN) error
                        if (roster.isEmpty()) {
                            System.out.println("[Notice] The roster is empty. No statistics to calculate.");
                        } else {
                            double sum = 0;
                            double highestGrade = -1;
                            String topStudent = "";

                            // 2. Loop through every student in the roster
                            for (Student s : roster) {
                                // Add their grade to our running total
                                sum += s.grade;

                                // 3. RUBRIC REQUIREMENT: Identify top-performing student
                                // If this student's grade is higher than our current highest, update the records
                                if (s.grade > highestGrade) {
                                    highestGrade = s.grade;
                                    topStudent = s.name;
                                }
                            }

                            // 4. Calculate the average by dividing the sum by the total number of students
                            double average = sum / roster.size();

                            // 5. Print the formatted statistics
                            System.out.println("\n--- Class Statistics ---");

                            // %.2f shows exactly two decimal places for the average
                            System.out.printf("Class average: %.2f (%s)%n", average, letterFor(average));
                            // %.1f shows one decimal place for the top student's grade, matching the checkpoint format
                            System.out.printf("Top Student:   %s with a %.1f (%s)%n", topStudent, highestGrade, letterFor(highestGrade));
                        }
                    }
                    else if (choice == 4) {
                        running = false;
                        System.out.println("Exiting Grade Tracker. Goodbye!");
                    }
                    else {
                        System.out.println("[Error] Invalid selection. Please choose 1-4.");
                    }
                } else {
                    System.out.println("[Error] Invalid input. Please enter a number.");
                    sc.next();
                }
            }
            sc.close();
        }
    }

    class Student {
        String name;
        double grade;

        public Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }
    }