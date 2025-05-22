package item.management.system;
class EmptyQueueException extends Exception {
    public EmptyQueueException(String message) {
        super(message);
    }
}
class PQNode<E> {
    private int key;
    private E data;
    private PQNode<E> next;

    public PQNode(int key, E data, PQNode<E> next) {
        this.key = key;
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public PQNode<E> getNext() {
        return next;
    }

    public void setNext(PQNode<E> newNext) {
        next = newNext;
    }

    public Item getElement() {
        return (Item) data;
    }
}

public class LinkedPriorityQueue<E> implements IPriorityQueue<E> {
    private int Qsize = 0;
    private PQNode<E> head = null;

    public boolean isEmpty() {
        return (Qsize == 0);
    }

    public int size() {
        return Qsize;
    }

    @Override
    public void insert(int k, E e) {
        PQNode<E> newPQNode = new PQNode<E>(k, e, null);
    if (head == null || k > head.getKey()) {
        newPQNode.setNext(head);
        head = newPQNode;
    } else {
        PQNode<E> current = head;
        while (current.getNext() != null && current.getNext().getKey() >= k) {
            current = current.getNext();
        }
        newPQNode.setNext(current.getNext());
        current.setNext(newPQNode);
    }
        Qsize++;
    }

    public E max() {
        return head.getData();
    }

    public E removeMax() throws EmptyQueueException {
        if (head != null) {
            E max = head.getData();
            head = head.getNext();
            return max;
        } else {
            throw new EmptyQueueException("Empty Stack");
        }
    }

    public PQNode <E> getHead() {
        return head;
    }
}