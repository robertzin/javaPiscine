
public class User {
    private int identifier;
    private String name;
    private double balance;

    public boolean isReadyToOutcome(double amount) {
        if (balance - amount >= 0)
            return true;
        System.out.println("Impossible to transfer " + amount + " from " + name);
        return false;
    }

    public void makeIncome(double amount) {
        balance += amount;
    }

    public void makeOutcome(double amount) {
        balance -= amount;
    }

    public void showBalance() {
        System.out.println("Balance of " + name + " is: " + balance);
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            this.balance = 0;
        } else
            this.balance = balance;
    }
}
