package stack;

import java.util.ArrayList;

public class ListStack<X> implements Stack<X> {

    private ArrayList<X> data;
    private int stackPointer;

    public ListStack() {
        data = new ArrayList<>();
        stackPointer = 0;
    }

    public void push(X newItem) {
        data.add(newItem);
        stackPointer++;
    }

    public X pop() {
        if (stackPointer == 0) {
            throw new IllegalStateException("No more items on the stack");
        }

        return data.remove(--stackPointer);
    }

    public boolean contains(X item) {
        return data.contains(item);
    }

    public X access(X item) {
        while (stackPointer > 0) {
            X tempItem = pop();
            if (tempItem.equals(item))
                return tempItem;
        }

        throw new IllegalArgumentException("Could not find item on the stack: " + item);
    }

    public int size() {
        return data.size();
    }
}