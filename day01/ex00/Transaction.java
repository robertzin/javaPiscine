import java.util.UUID;

enum transferType {
    outcome,
    income;
}

public class Transaction {
    private UUID identifier;
    private User recipipent;
    private User sender;
    private transferType transferCategory;
    private double transferAmount;

    public boolean makeTransaction() {
        double amount = transferAmount;
        boolean success = false;

        if (amount <= 0) {
            return success;
        }

        if (sender.isReadyToOutcome(amount)) {
            sender.makeOutcome(amount);
            recipipent.makeIncome(amount);
            success = true;
        }
        return success;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipipent() {
        return recipipent;
    }

    public User getSender() {
        return sender;
    }

    public transferType getTransferCategory() {
        return transferCategory;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setRecipipent(User recipipent) {
        this.recipipent = recipipent;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTransferCategory(transferType transferCategory) {
        this.transferCategory = transferCategory;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }
}
