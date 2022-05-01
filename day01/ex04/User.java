
public class User {
    private int identifier;
    private String name;
    private double balance;
    private TransactionsLinkedList transactionList = new TransactionsLinkedList();

    public User(String name, double balance) {
        this.identifier = this.identifier =UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }

    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public TransactionsLinkedList getTransactionList() {
        return transactionList;
    }

    public void addTransactionToList(Transaction transaction) {
        transactionList.addTransaction(transaction);
    }

    public void printTransactionsList() {
        Transaction[] array = transactionList.transformToArray();

        System.out.println("\nList of transactions for " + name + ":");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }


    @Override
    public String toString() {
        return "User ID: " + identifier + ", name: " + name + ", balance: " + balance;
    }
}
