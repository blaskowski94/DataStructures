package hash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BasicHashTable<K, V> {
    private HashEntry[] data;
    private int capacity, size;

    public BasicHashTable(int tableSize) {
        this.capacity = tableSize;
        this.data = new HashEntry[this.capacity];
        this.size = 0;
    }

    public V get(K key) {
        int hash = calculateHash(key);

        if (data[hash] == null) return null;

        return (V) data[hash].getValue();
    }

    public void put(K key, V value) {
        int hash = calculateHash(key);

        data[hash] = new HashEntry(key, value);
        size++;
    }

    public V delete(K key) {
        int hash = calculateHash(key);

        if (data[hash] == null) return null;

        V value = (V) data[hash].getValue();
        data[hash] = null;
        size--;
        hash = (hash + 1) % capacity;

        // correct subsequent values whose hash was affected by collision
        while (data[hash] != null) {
            HashEntry he = data[hash];
            data[hash] = null;
            put((K) he.getKey(), (V) he.getValue());
            size--;
            hash = (hash + 1) % capacity;
        }
        return value;
    }

    public boolean hasKey(K key) {
        int hash = calculateHash(key);

        if (data[hash] == null) return false;
        return data[hash].getKey().equals(key);
    }

    public boolean hasValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if(data[i] != null && data[i].getValue().equals(value)) return true;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    private int calculateHash(K key) {
        int hash = (key.hashCode() % this.capacity);

        // deal with collisions
        while (data[hash] != null && !data[hash].getKey().equals(key)) {
            hash = (hash + 1) % capacity;
        }
        return hash;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class HashEntry<X, Y> {
        private X key;
        private Y value;
    }
}
