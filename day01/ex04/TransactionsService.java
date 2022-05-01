import java.util.UUID;

public class TransactionsService {
    private UsersArrayList userList = new UsersArrayList();
    private TransactionsLinkedList allTransactions = new TransactionsLinkedList();

    public UsersArrayList getUserList() {
        return userList;
    }

    class IllegalTransactionException extends RuntimeException {
    }

    public void addUser(String name, double balance) {
        userList.addUser(new User(name, balance));
    }

    public void addUser(User user) {
        userList.addUser(user);
    }

    public double retrieveBalance(User someUser) {
        User user = userList.retrieveByID(someUser.getIdentifier());
        return user.getBalance();
    }

    public void makeTransaction(int recepientID, int senderID, double amount) {

        User recipient = userList.retrieveByID(recepientID);
        User sender = userList.retrieveByID(senderID);
        if (recipient == null || sender == null || sender.getBalance() - amount < 0) {
            throw new IllegalTransactionException();
        }

        UUID transactionID = UUID.randomUUID();
        Transaction debit = new Transaction(transactionID, recipient, sender, transferType.outcome, amount);
        Transaction credit = new Transaction(transactionID, recipient, sender, transferType.income, amount);
        if (debit.makeTransaction())
            allTransactions.addTransaction(debit);
        if (credit.makeTransaction())
            allTransactions.addTransaction(credit);
    }

    public Transaction[] retrieveTransaction(int ID) {
        return userList.retrieveByID(ID).getTransactionList().transformToArray();
    }

    public Transaction[] checkTransactions() {
        TransactionsLinkedList unPaired = new TransactionsLinkedList();
        Transaction[] array = allTransactions.transformToArray();

        for (Transaction transaction : array) {
            if (allTransactions.countTransactionsById(transaction.getIdentifier()) % 2 != 0 && !unPaired.isInList(transaction.getIdentifier())) {
                unPaired.addTransaction(transaction);
            }
        }
        return unPaired.transformToArray();
    }
}
