package hash.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hash.BasicHashTable;

import static org.junit.jupiter.api.Assertions.*;

class BasicHashTableTest {

    private BasicHashTable<Integer, String> hashTable;

    @BeforeEach
    void setup() {
        hashTable = new BasicHashTable<>(10);
    }

    @Test
    void getTest() {
        assertNull(hashTable.get(0));

        hashTable.put(0, "0");
        assertEquals("0", hashTable.get(0));
        assertEquals(1, hashTable.size());

        hashTable.put(0, "1");
        assertEquals("1", hashTable.get(0));
        assertEquals(1, hashTable.size());

        hashTable.put(1, "2");
        hashTable.put(2, "3");

        assertEquals("1", hashTable.get(0));
        assertEquals("2", hashTable.get(1));
        assertEquals("3", hashTable.get(2));
        assertEquals(3, hashTable.size());
    }

    @Test
    void putTest() {
        for (int i = 0; i < 10; i++) {
            hashTable.put(i, Integer.toString(i));
        }
        assertEquals(10, hashTable.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(Integer.toString(i), hashTable.get(i));
        }
    }

    @Test
    void putFullTest() {
        Exception ex = assertThrows(IllegalStateException.class, () -> {
            for (int i = 0; i < 11; i++) {
                hashTable.put(i, Integer.toString(i));
            }
        });
        assertEquals("HashTable is full", ex.getMessage());
    }

    @Test
    void putCollisionTest() {
        BasicHashTable<String, String> stringHashTable = new BasicHashTable<>(10);

        // should have same hash code
        String string1 = "abyz", string2 = "abz[";
        System.out.println("String 1: \"" + string1 + "\" hashCode: " + string1.hashCode());
        System.out.println("String 2: \"" + string2 + "\" hashCode: " + string2.hashCode());
        System.out.println("string1.hashCode() == string2.hashCode(): " + (string1.hashCode() == string2.hashCode()));

        stringHashTable.put(string1, "value1");
        stringHashTable.put(string2, "value2");

        assertEquals("value1", stringHashTable.get(string1));
        assertEquals("value2", stringHashTable.get(string2));
        assertEquals(2, stringHashTable.size());
    }

    @Test
    void deleteTest() {
        assertNull(hashTable.delete(1));

        hashTable.put(0, "0");
        assertEquals("0", hashTable.delete(0));
        assertEquals(0, hashTable.size());

        hashTable.put(0, "0");
        hashTable.put(1, "1");
        hashTable.put(2, "2");

        assertEquals("1", hashTable.delete((1)));
        assertNull(hashTable.delete(1));
        assertEquals(2, hashTable.size());
        assertEquals("2", hashTable.delete(2));
        assertEquals(1, hashTable.size());
        assertEquals("0", hashTable.delete(0));
        assertEquals(0, hashTable.size());
    }

    @Test
    void hasKeyTest() {
        assertFalse(hashTable.hasKey(0));
        hashTable.put(0, "0");
        assertTrue(hashTable.hasKey(0));
        assertFalse(hashTable.hasKey(1));
    }

    @Test
    void hasValueTest() {
        assertFalse(hashTable.hasValue("0"));
        hashTable.put(0, "0");
        assertTrue(hashTable.hasValue("0"));
        assertFalse(hashTable.hasValue("1"));
    }

    @Test
    void sizeTest() {
        assertEquals(0, hashTable.size());
        hashTable.put(0, "0");
        assertEquals(1, hashTable.size());
        hashTable.put(0, "0");
        assertEquals(1, hashTable.size());
        hashTable.put(0, "1");
        assertEquals(1, hashTable.size());

        hashTable.put(1, "1");
        assertEquals(2, hashTable.size());

        hashTable.delete(1);
        assertEquals(1, hashTable.size());

        hashTable.delete(5);
        assertEquals(1, hashTable.size());
    }
}
