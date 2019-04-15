package stack;

public class BasicStack<X> implements Stack<X> {

    private X[] data;
    private int stackPointer;

    public BasicStack() {
        data = (X[]) new Object[1000];
        stackPointer = 0;
    }

    public void push(X newItem) {
        if (stackPointer == data.length) {
            X[] temp = (X[]) new Object[data.length*2];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
        }
        data[stackPointer++] = newItem;
    }

    public X pop() {
        if (stackPointer == 0) {
            throw new IllegalStateException("No more items on the stack");
        }
        return data[--stackPointer];
    }

    public boolean contains(X item) {
        for (int i = stackPointer - 1; i >= 0; i--) {
            if (data[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public X access(X item) {
        while(stackPointer > 0) {
            X tempItem = pop();
            if (tempItem.equals(item))
                return tempItem;
        }

        throw new IllegalArgumentException("Could not find item on the stack: " + item);
    }

    public int size() {
        return stackPointer;
    }
}