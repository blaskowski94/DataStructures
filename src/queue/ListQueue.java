package queue;

import java.util.ArrayList;

public class ListQueue<X> implements Queue<X> {
    private ArrayList<X> data;
    private int queuePointer, queueLength;

    public ListQueue() {
        data = new ArrayList<>();
        queuePointer = 0;
        queueLength = 0;
    }

    public void enQueue(X item) {
        data.add(item);
        queueLength++;
    }

    public X deQueue() {
        if (queueLength == 0) {
            throw new IllegalStateException("No items in queue");
        }

        queueLength--;
        return data.get(queuePointer++);
    }

    public boolean contains(X item) {
        for (int i = queuePointer; i < queuePointer + queueLength; i++) {
            if (data.get(i).equals(item)) return true;
        }
        return false;
    }

    public X access(X item) {
        for (int i = queuePointer; i < queuePointer + queueLength; i++) {
            X queueItem = deQueue();
            if (queueItem.equals(item)) return queueItem;
        }
        return null;
    }

    public X access(int position) {
        if (position >= queueLength) return null;

        int truePosition = 0;
        for (int i = queuePointer; i < queuePointer + queueLength; i++) {
            X queueItem = deQueue();
            if (truePosition == position) return queueItem;
            truePosition++;
        }
        return null;
    }

    public int size() {
        return queueLength;
    }
}
