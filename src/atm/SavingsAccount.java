package atm;

public class SavingsAccount extends Account {
    public static final double MAINTAINING_BALANCE = 500.0;
    private double interestRate;

    public SavingsAccount(String accountNumber, String ownerName, double openingBalance,
                          double interestRate) {
        super(accountNumber, ownerName, openingBalance);
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }

        double remaining = getBalance() - amount;

        if (remaining < MAINTAINING_BALANCE) {
            double shortfall = MAINTAINING_BALANCE - remaining;
            throw new InsufficientFundsException(shortfall);
        }

        super.withdraw(amount);
    }

    public double monthlyInterest() {
        return getBalance() * interestRate / 12;
    }
}

/*
  THINK ABOUT IT - write your answer as a comment in the file
 1. Why is getAccountType() declared abstract in Account
    instead of just returning "ACCOUNT"?

    getAccountType() is abstract so every subclass is required to provide
    its own account type. This allows each account type to behave through
    the same Account reference while providing its own result.

 2. Why can SavingsAccount call super.withdraw(amount) and applyWithdrawal(amount),
    but cannot read the private balance field by name?

    SavingsAccount can use inherited methods and protected members, but
    balance is private to Account. It must use methods such as getBalance()
    instead of directly accessing the balance field.
 */