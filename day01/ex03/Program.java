import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Rob", 209.05);
        User user2 = new User("Bob", 190);
        User user3 = new User("Cop", 32.50);
        User user4 = new User("Bot", 10090);
        User user5 = new User("SWAT", 90.01);

        UsersArrayList userList = new UsersArrayList();
        userList.addUser(user1);
        userList.addUser(user2);
        userList.addUser(user3);
        userList.addUser(user4);
        userList.addUser(user5);

        TransactionsLinkedList list = new TransactionsLinkedList();
        list.addTransaction(new Transaction(UUID.randomUUID(), user1, user2, transferType.outcome, 102.03));
        list.addTransaction(new Transaction(UUID.randomUUID(), user2, user3, transferType.outcome, 20));
        list.addTransaction(new Transaction(UUID.randomUUID(), user3, user4, transferType.outcome, 30));
        list.addTransaction(new Transaction(UUID.randomUUID(), user4, user5, transferType.outcome, 5));
        list.addTransaction(new Transaction(UUID.randomUUID(), user5, user1, transferType.outcome, 10.70));

        Transaction[] listArray = list.transformToArray();
        for (int i = 0; i < list.getSize(); i++) {
            listArray[i].makeTransaction();
            System.out.println(listArray[i]);
        }

        User[] userArray = userList.getUserArray();
        for (int i = 0; i < userArray.length; i++) {
            userArray[i].printTransactionsList();
        }
    }
}