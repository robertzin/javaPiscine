import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private Node first;
    private Node last;
    private int size = 0;

    public int getSize() {
        return size;
    }

    static class TransactionNotFoundException extends RuntimeException {
    }

    private class Node {
        Transaction transaction;
        Node next;
        Node prev;


        public Node(Transaction transaction, Node next, Node prev) {
            this.transaction = transaction;
            this.next = next;
            this.prev = prev;
        }


        public Transaction getTransaction() {
            return transaction;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setTransaction(Transaction transaction) {
            this.transaction = transaction;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    @Override
    public void addTransaction(Transaction transactionToAdd) {
        if (size == 0) {
            Node newNode = new Node(transactionToAdd, null, null);
            first = newNode;
            last = newNode;
        } else {
            Node newNode = new Node(transactionToAdd, null, last);
            last.setNext(newNode);
            last = last.getNext();
        }
        size++;
    }

    @Override
    public void removeByID(UUID uuid) {
        for (Node tmp = first; tmp != null; tmp = tmp.next) {
            if (tmp.getTransaction().getIdentifier().equals(uuid)) {
                remove(tmp);
            }
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] transformToArray() {
        Transaction[] retArray = new Transaction[size];
        Node tmp = first;

        for (int i = 0; i < size; i++) {
            retArray[i] = tmp.getTransaction();
            tmp = tmp.next;
        }
        return retArray;
    }

    public void remove(Node node) {
        Node next = node.next;
        Node prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.transaction = null;
        size--;
    }

    public int countTransactionsById(UUID id) {
        int ret = 0;

        for (Node tmp = first; tmp != null; tmp = tmp.next) {
            if (tmp.getTransaction().getIdentifier().equals(id)) {
                ret++;
            }
        }
        return ret;
    }

    public boolean isInList(UUID uuid) {
        for (Node tmp = first; tmp != null; tmp = tmp.next) {
            if (tmp.getTransaction().getIdentifier().equals(uuid))
                return true;
        }
        return false;
    }
}
