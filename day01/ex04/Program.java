import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsService bankSystem = new TransactionsService();

        User user1 = new User("Rob", 209.05);
        User user2 = new User("Bob", 190);
        User user3 = new User("Cop", 32.50);
        User user4 = new User("Bot", 10090);
        User user5 = new User("SWAT", 90.01);

        bankSystem.addUser(user1);
        bankSystem.addUser(user2);
        bankSystem.addUser(user3);
        bankSystem.addUser(user4);
        bankSystem.addUser(user5);

        System.out.println("Before transaction: " + bankSystem.retrieveBalance(user1) + " / " + bankSystem.retrieveBalance(user2));
        bankSystem.makeTransaction(user1.getIdentifier(), user2.getIdentifier(), 12.50);
        System.out.println("Aftre transaction: " + bankSystem.retrieveBalance(user1) + " / " + bankSystem.retrieveBalance(user2));

        bankSystem.makeTransaction(user4.getIdentifier(), user5.getIdentifier(), 20.75);
//        bankSystem.makeTransaction(user3.getIdentifier(), user1.getIdentifier(), 3920.50);
        bankSystem.makeTransaction(user5.getIdentifier(), user1.getIdentifier(), 1.50);

        for (User user: bankSystem.getUserList().getUserArray()) {
            for (Transaction transaction: user.getTransactionList().transformToArray())
                System.out.println(transaction);
        }

        System.out.println("Unpaired transactions: " + bankSystem.checkTransactions().length);
    }
}