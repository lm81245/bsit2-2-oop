package atm;

import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ATMService atm = new ATMService();
        Account account = new SavingsAccount("SV-2007", "Lorraine Mhae", 5000.0, 0.02);

        boolean running = true;
        while (running) {
            printHeader(account);
            printMenu();
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        System.out.printf("Balance: PHP %.2f%n", account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depAmount = Double.parseDouble(input.nextLine());
                        atm.deposit(account, depAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withAmount = Double.parseDouble(input.nextLine());
                        account.withdraw(withAmount);
                        System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n",
                                withAmount, account.getBalance());
                        break;
                    case 4:
                        double total = atm.depositAll(account, 100, 250.5, 300);
                        System.out.printf("Deposited multiple amounts totaling PHP %.2f. New balance: PHP %.2f%n", total, account.getBalance());
                        break;
                    case 5:
                        System.out.printf("Balance before demo: PHP %.2f%n", account.getBalance());
                        atm.tryToReplace(account);
                        System.out.printf("Balance after tryToReplace: PHP %.2f%n",
                                account.getBalance());
                        atm.addBonus(account, 50);
                        System.out.printf("Balance after addBonus: PHP %.2f%n",
                                account.getBalance());
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Choose between options 0-5");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please type numbers only.");
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.printf("You need PHP %.2f more.%n", e.getShortfall());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("---------------------------------");
            }
        }
        input.close();
        System.out.println("Thank you for using Liceo ATM!");
    }

    private static void printHeader(Account account) {
        System.out.println("=================================");
        System.out.println(" LICEO ATM MACHINE");
        System.out.println("=================================");
        System.out.println("Account Number: " + account.getAccountNumber() + " (" +
                account.getOwnerName() + ")");
        System.out.println("Account Type    : " + account.getAccountType());
        System.out.printf("Account Balance : PHP %.2f%n", account.getBalance());
        System.out.println("---------------------------------");
    }

    private static void printMenu() {
        System.out.println("[1] Check Account's Balance");
        System.out.println("[2] Deposit Cash");
        System.out.println("[3] Withdraw Cash");
        System.out.println("[4] Deposit Multiple Amounts");
        System.out.println("[5] Pass-by-Value Demo");
        System.out.println("[0] Exit Transaction");
    }
}