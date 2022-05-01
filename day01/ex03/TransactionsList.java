import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transactionToAdd);
    public void removeByID(UUID uuid);
    public Transaction[] transformToArray();
}
