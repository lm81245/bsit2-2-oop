public class Account {

    // Encapsulated account data.
    private String owner;
    private double balance;

    // Initialize the account with a non-negative opening balance.
    public Account(String owner, double openingBalance) {
        this.owner = owner;

        // Prevent the account from starting with a negative balance.
        if (openingBalance < 0) {
            this.balance = 0.0;
        } else {
            this.balance = openingBalance;
        }
    }

    // Return the account owner's name.
    public String getOwner() {
        return owner;
    }

    // Return the current account balance.
    public double getBalance() {
        return balance;
    }

    // Deposit only positive amounts.
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        this.balance += amount;
        System.out.println("Deposited " + amount + ". New balance: " + this.balance);
    }

    // Withdraw only positive amounts within the available balance.
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        // Prevent the balance from becoming negative.
        if (amount > this.balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        this.balance -= amount;
        System.out.println("Withdrew " + amount + ". New balance: " + this.balance);
    }
}