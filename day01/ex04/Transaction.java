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

    public Transaction(UUID identifier, User recipipent, User sender, transferType transferCategory, double transferAmount) {
        this.identifier = identifier;
        this.recipipent = recipipent;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
    }

    public boolean makeTransaction() {
        double amount = transferAmount;
        boolean success = false;

        if (amount <= 0) {
            return success;
        }
        if (transferCategory == transferType.outcome) {
            if (sender.isReadyToOutcome(amount)) {
                sender.makeOutcome(amount);
                sender.addTransactionToList(this);
                success = true;
            }
        } else if (transferCategory == transferType.income) {
            recipipent.makeIncome(amount);
            recipipent.addTransactionToList(this);
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

    @Override
    public String toString() {
        return ("Transaction ID: " + identifier + ", recipient: " + recipipent.getName() + ", sender: " + sender.getName() + ", category: " + transferCategory + ", amount: " + transferAmount);
    }
}
