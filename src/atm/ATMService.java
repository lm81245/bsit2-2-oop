package atm;

public class ATMService {

    public void deposit(Account account, double amount) {
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n", amount,
                account.getBalance());
    }

    public void deposit(Account account, double amount, String note) {
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2f (%s). New balance: PHP %.2f%n", amount, note,
                account.getBalance());
    }

    public double depositAll(Account account, double... amounts) {
        double total = 0;
        for (double amt : amounts) {
            account.deposit(amt);
            total += amt;
        }
        return total;
    }

    public void tryToReplace(Account account) {
        account = new SavingsAccount("XX-000", "Ghost Account", 0, 0);
        System.out.println("Inside tryToReplace method: " + account);
        // Java passes the reference by value, so reassigning account only changes
        // the local copy of the reference. The caller still points to the original account.
    }

    public void addBonus(Account account, double bonus) {
        account.deposit(bonus);
        // The reference is copied, but both references point to the same object.
        // Changing the object's balance is therefore visible to the caller.
    }

    public void transfer(Account from, Account to, double amount) throws InsufficientFundsException
    {
        from.withdraw(amount);
        to.deposit(amount);
    }
}