import java.util.Scanner;

// Helper class for Task 5
class Box {
    int value;

    Box(int value) {
        this.value = value;
    }
}

/**
 * Student Name: Lorraine Cabatuan
 * Course: Object-Oriented Programming
 * Lab Activity 4: Methods & Parameter Passing
 */
public class ToolBox {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("=========================================");
        System.out.println(" Welcome to Lorraine Cabatuan's Java Toolbox App ");
        System.out.println("=========================================");

        while (choice != 0) {
            System.out.println("\n===== JAVA TOOLBOX =====");
            System.out.println("1 - Greet me");
            System.out.println("2 - Area (square or rectangle)");
            System.out.println("3 - Sum of numbers");
            System.out.println("4 - Swap demo (pass-by-value)");
            System.out.println("5 - Box demo (object mutation)");
            System.out.println("0 - Exit");
            
            // Validate main menu choice (0 to 5)
            choice = readBoundedInt(scanner, "Choose an option: ", 0, 5, 
                "Invalid menu choice. Enter a whole number from 0 to 5.");

            System.out.println("-----------------------------------------");

            switch (choice) {
                case 1:
                    String name = readValidName(scanner, "Enter your name: ");
                    System.out.println("\nResult: " + greet(name));
                    break;

                case 2:
                    int shapeChoice = readBoundedInt(scanner, 
                        "Sides (1 = square, 2 = rectangle): ", 1, 2, 
                        "Invalid selection. Please enter 1 for square or 2 for rectangle.");

                    if (shapeChoice == 1) {
                        double side = readPositiveDouble(scanner, "Enter side length: ");
                        System.out.printf("Calculation complete: Area of square = %.2f\n", area(side));
                    } else {
                        double length = readPositiveDouble(scanner, "Enter length: ");
                        double width = readPositiveDouble(scanner, "Enter width: ");
                        System.out.printf("Calculation complete: Area of rectangle = %.2f\n", area(length, width));
                    }
                    break;

                case 3:
                    int count = readBoundedInt(scanner, 
                        "How many numbers would you like to add? (1-15): ", 1, 15, 
                        "Invalid quantity. You can enter between 1 and 15 numbers.");

                    int[] userNumbers = new int[count];
                    for (int i = 0; i < count; i++) {
                        userNumbers[i] = readInt(scanner, "Enter number " + (i + 1) + ": ", 
                            "Invalid input. Please enter a valid whole number.");
                    }

                    // Formats number list matching sample run format
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < userNumbers.length; i++) {
                        sb.append(userNumbers[i]);
                        if (i < userNumbers.length - 1) {
                            sb.append(", ");
                        }
                    }

                    // Passes array to varargs parameter method
                    int total = sum(userNumbers);
                    System.out.println("\nSum of " + sb + " = " + total);
                    break;

                case 4:
                    int x = 5;
                    int y = 9;
                    System.out.println("Before swap: x = " + x + ", y = " + y);
                    swap(x, y);
                    System.out.println("After swap:  x = " + x + ", y = " + y + "  (unchanged - Java is pass-by-value)");
                    break;

                case 5:
                    Box box = new Box(10);
                    System.out.println("Before: box.value = " + box.value);
                    addToBox(box, 25);
                    System.out.println("After:  box.value = " + box.value + "  (changed - the object is shared)");
                    break;

                case 0:
                    System.out.println("Goodbye! Thanks for using Java Toolbox.");
                    break;
            }

            if (choice != 0) {
                System.out.println("Operation finished successfully.");
                System.out.println("-----------------------------------------");
            }
        }

        scanner.close();
    }

    // Task 1 — greet (parameter + return)
    static String greet(String name) {
        return "Hello, " + name + "! Welcome to my Java Toolbox.";
    }

    // Task 2 — area (overloading: square)
    static double area(double side) {
        return side * side;
    }

    // Task 2 — area (overloading: rectangle)
    static double area(double length, double width) {
        return length * width;
    }

    // Task 3 — sum (varargs)
    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // Task 4 — swap (pass-by-value)
    static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("   (inside swap)  a = " + a + ", b = " + b);
    }

    // Task 5 — addToBox (object mutation)
    static void addToBox(Box box, int amount) {
        box.value = box.value + amount;
    }

    // --- HELPER INPUT VALIDATIONS ---

    // Reads any valid whole integer safely
    private static int readInt(Scanner scanner, String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: " + errorMsg);
            }
        }
    }

    // Reads an integer bounded by min and max
    private static int readBoundedInt(Scanner scanner, String prompt, int min, int max, String errorMsg) {
        while (true) {
            int val = readInt(scanner, prompt, "Please enter a valid whole number.");
            if (val >= min && val <= max) {
                return val;
            }
            System.out.println("Error: " + errorMsg);
        }
    }

    // Reads a double value strictly greater than 0
    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double val = Double.parseDouble(input);
                if (val > 0) {
                    return val;
                }
                System.out.println("Error: Measurement cannot be zero or negative.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. Enter a valid decimal or whole number.");
            }
        }
    }

    // Validates name: letters, spaces, hyphens, and apostrophes only
    private static String readValidName(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println("Error: Name cannot be empty or blank.");
            } else if (!input.matches("^[a-zA-Z\\s'-]+$")) {
                System.out.println("Error: Name can only contain letters, spaces, hyphens, or apostrophes.");
            } else {
                return input;
            }
        }
    }
}