package queue;

public class BasicQueue<X> implements Queue<X> {
    private X[] data;
    private int queuePointer, queueLength;

    public BasicQueue() {
        data = (X[]) new Object[10];
        queuePointer = 0;
        queueLength = 0;
    }

    public void enQueue(X item) {
        if (queuePointer + queueLength >= data.length) {
            X[] temp = (X[]) new Object[data.length * 2];
            System.arraycopy(data, queuePointer, temp, 0, queueLength);
            data = temp;
            queuePointer = 0;
        }
        data[queuePointer + queueLength++] = item;
    }

    public X deQueue() {
        if (queueLength == 0) {
            throw new IllegalStateException("No items in queue");
        }

        queueLength--;
        return data[queuePointer++];
    }

    public boolean contains(X item) {
        for (int i = queuePointer; i < queuePointer + queueLength; i++) {
            if (data[i].equals(item)) return true;
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
