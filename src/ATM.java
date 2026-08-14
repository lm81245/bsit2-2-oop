import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Initialize the account with its starting balance.
        Account account = new Account("Juan Dela Cruz", 1000.0);

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("===== WELCOME TO CLI ATM =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        // Retrieve account information through getters.
                        System.out.println("Account Holder: " + account.getOwner());
                        System.out.println("Current Balance: " + account.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = input.nextDouble();
                        account.deposit(depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = input.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;

                    case 4:
                        running = false;
                        System.out.println("Thank you for using CLI ATM!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                // Handle non-numeric input and clear the invalid entry.
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }

        input.close();
    }
}