import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User userRob = new User();
        userRob.setName("Rob");
        userRob.setIdentifier(2);
        userRob.setBalance(-10.12);

        User userBob = new User();
        userBob.setName("Bob");
        userBob.setIdentifier(3);
        userBob.setBalance(127834.03);

        Transaction transaction_1 = new Transaction();
        transaction_1.setIdentifier(UUID.randomUUID());
        transaction_1.setRecipipent(userBob);
        transaction_1.setSender(userRob);
        transaction_1.setTransferCategory(transferType.outcome);
        transaction_1.setTransferAmount(12.45);

        Transaction transaction_2 = new Transaction();
        transaction_2.setIdentifier(UUID.randomUUID());
        transaction_2.setRecipipent(userRob);
        transaction_2.setSender(userBob);
        transaction_2.setTransferCategory(transferType.income);
        transaction_2.setTransferAmount(30.10);

        userBob.showBalance();
        userRob.showBalance();
        System.out.println("amount: " + transaction_2.getTransferAmount());
        transaction_2.makeTransaction();
        userBob.showBalance();
        userRob.showBalance();
        System.out.println("amount: " + transaction_1.getTransferAmount());
        transaction_1.makeTransaction();
        userBob.showBalance();
        userRob.showBalance();
    }
}