/*

* ============================================================================
* LAB ACTIVITY 5 — Exception Handling & Debugging
* Program : Mini ATM (Command-Line Interface)
* Course  : Object-Oriented Programming (Java)
* ============================================================================
  */

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MiniATM {

// Stores the current account balance.
static double balance = 1000.00;

// Reads all user input from the console.
static Scanner input = new Scanner(System.in);

public static void main(String[] args) {
    System.out.println("=========================================");
    System.out.println("        WELCOME TO THE MINI ATM");
    System.out.println("=========================================");

    boolean running = true;

    // Keep showing the menu until the user exits.
    while (running) {
        printMenu();

        String choice;
        try {
            // Read and remove extra spaces from the menu choice.
            choice = input.nextLine().trim();
        } catch (NoSuchElementException e) {
            // Exit safely if the input stream is closed.
            System.out.println("\n[!] Input stream was closed. Exiting the program.");
            break;
        }

        // Run the selected menu option.
        switch (choice) {
            case "1":
                deposit();
                break;
            case "2":
                withdraw();
                break;
            case "3":
                checkBalance();
                break;
            case "4":
                running = false;
                System.out.println("\nThank you for using the Mini ATM. Goodbye!");
                break;
            default:
                // Handle invalid menu choices without stopping the program.
                System.out.println("\n[!] Please choose a number from 1 to 4.\n");
        }
    }
}

// Displays the available ATM options.
static void printMenu() {
    System.out.println("Current options:");
    System.out.println("  [1] Deposit");
    System.out.println("  [2] Withdraw");
    System.out.println("  [3] Check balance");
    System.out.println("  [4] Exit");
    System.out.print("Enter your choice: ");
}

// -------------------------------------------------------------------------
// DEPOSIT
// -------------------------------------------------------------------------
static void deposit() {
    System.out.print("Enter amount to deposit: ");

    try {
        // Read and remove extra spaces from the entered amount.
        String line = input.nextLine().trim();

        // Reject empty input before converting it to a number.
        if (line.isEmpty()) {
            System.out.println("[!] Input cannot be blank.");
            return;
        }

        // Convert the input from String to double.
        double amount = Double.parseDouble(line);

        // Reject NaN and infinite values.
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new InvalidAmountException("Please enter a valid, finite amount.");
        }

        // Deposits must be greater than zero.
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        // Update the balance only after all validations pass.
        balance += amount;
        System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n", amount, balance);

    } catch (NoSuchElementException e) {
        // Cancel the transaction if the input stream is closed.
        System.out.println("[!] Input stream was closed. Transaction cancelled.");
    } catch (NumberFormatException e) {
        // Handle letters, symbols, and other invalid number formats.
        System.out.println("[!] Please enter a valid number.");
    } catch (InvalidAmountException e) {
        // Display the custom validation error.
        System.out.println("[!] " + e.getMessage());
    } finally {
        // Always display the transaction completion message.
        System.out.println("-- transaction finished --\n");
    }
}

// -------------------------------------------------------------------------
// WITHDRAW
// -------------------------------------------------------------------------
static void withdraw() {
    System.out.print("Enter amount to withdraw: ");

    try {
        // Read and remove extra spaces from the entered amount.
        String line = input.nextLine().trim();

        // Reject empty input before converting it to a number.
        if (line.isEmpty()) {
            System.out.println("[!] Input cannot be blank.");
            return;
        }

        // Convert the input from String to double.
        double amount = Double.parseDouble(line);

        // Reject NaN and infinite values.
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new InvalidAmountException("Please enter a valid, finite amount.");
        }

        // Withdrawals must be greater than zero.
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        // Prevent withdrawals that exceed the current balance.
        if (amount > balance) {
            double shortfall = amount - balance;
            throw new InsufficientFundsException(
                String.format("Insufficient funds. You are short by PHP %.2f.", shortfall),
                shortfall
            );
        }

        // Update the balance only after all validations pass.
        balance -= amount;
        System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n", amount, balance);

    } catch (NoSuchElementException e) {
        // Cancel the transaction if the input stream is closed.
        System.out.println("[!] Input stream was closed. Transaction cancelled.");
    } catch (NumberFormatException e) {
        // Handle letters, symbols, and other invalid number formats.
        System.out.println("[!] Please enter a valid number.");
    } catch (InvalidAmountException | InsufficientFundsException e) {
        // Handle custom transaction errors using multi-catch.
        System.out.println("[!] " + e.getMessage());
    } finally {
        // Always display the transaction completion message.
        System.out.println("-- transaction finished --\n");
    }
}

// -------------------------------------------------------------------------
// CHECK BALANCE
// -------------------------------------------------------------------------

// Displays the current account balance.
static void checkBalance() {
    System.out.printf("%nYour current balance is: PHP %.2f%n%n", balance);
}

}

/*

* ============================================================================
* CUSTOM EXCEPTIONS
* ============================================================================
  */

// Stores the shortfall when the balance is insufficient.
class InsufficientFundsException extends Exception {
private final double shortfall;

// Creates an exception with a message and no shortfall value.
public InsufficientFundsException(String message) {
    super(message);
    this.shortfall = 0.0;
}

// Creates an exception with a message and the required shortfall.
public InsufficientFundsException(String message, double shortfall) {
    super(message);
    this.shortfall = shortfall;
}

// Returns the amount needed to complete the withdrawal.
public double getShortfall() {
    return shortfall;
}

}

// Handles zero, negative, and other invalid transaction amounts.
class InvalidAmountException extends Exception {

// Creates an exception with a descriptive error message.
public InvalidAmountException(String message) {
    super(message);
}

}
