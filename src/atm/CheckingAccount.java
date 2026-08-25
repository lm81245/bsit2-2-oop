package atm;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String ownerName, double openingBalance,
                           double overdraftLimit) {
        super(accountNumber, ownerName, openingBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }

        double remaining = getBalance() - amount;

        if (remaining < -overdraftLimit) {
            double shortfall = -overdraftLimit - remaining;
            throw new InsufficientFundsException(shortfall);
        }

        applyWithdrawal(amount);
    }
}