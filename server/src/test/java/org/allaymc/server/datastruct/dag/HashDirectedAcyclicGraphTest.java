package org.allaymc.server.datastruct.dag;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cool_Loong
 */
public class HashDirectedAcyclicGraphTest {
    static HashDirectedAcyclicGraph<Base> setABCDEFG;

    @BeforeAll
    public static void setUp() {
        setABCDEFG = new HashDirectedAcyclicGraph<>();
        setABCDEFG.add(Base.A);
        setABCDEFG.add(Base.B);
        setABCDEFG.add(Base.C);
        setABCDEFG.add(Base.D);
        setABCDEFG.add(Base.E);
        setABCDEFG.add(Base.F);
        setABCDEFG.add(Base.G);
    }

    /**
     * Test of getBefore method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testGetBefore() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        // Test 1
        try {
            instance.setBefore(Base.A, Base.B);
            instance.setBefore(Base.F, Base.B);
            instance.setBefore(Base.G, Base.B);
            instance.setBefore(Base.B, Base.C);
            Set<Base> expResult = new HashSet<>();
            expResult.add(Base.A);
            expResult.add(Base.F);
            expResult.add(Base.G);
            Set<Base> result = instance.getBefore(Base.B);
            assertEquals(expResult, result);
        } catch (DAGCycleException ignore) {
        }

        // Test 2
        Assertions.assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.B, Base.B);
        });
        // Test 3
        Assertions.assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.C, Base.B);
        });
    }

    /**
     * Test of isBefore method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testIsBefore() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        try {
            instance.setBefore(Base.A, Base.B);
            instance.setBefore(Base.F, Base.B);
            instance.setBefore(Base.G, Base.B);
            instance.setBefore(Base.B, Base.C);
        } catch (Exception ignore) {
        }

        // Test 1
        boolean expResult = true;
        boolean result = instance.isBefore(Base.A, Base.B);
        assertEquals(expResult, result);
        // Test 2
        result = instance.isBefore(Base.B, Base.A);
        assertFalse(result);
        // Test 3
        result = instance.isBefore(Base.A, Base.A);
        assertFalse(result);
        // Test 4
        result = instance.isBefore(Base.A, Base.D);
        assertFalse(result);
        // Test 5
        result = instance.isBefore(Base.D, Base.A);
        assertFalse(result);
        // Test 6
        result = instance.isBefore(Base.D, Base.D);
        assertFalse(result);
        // Test 5
        result = instance.isBefore(Base.A, Base.C);
        assertTrue(result);
        // Test 6
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            instance.isBefore(Base.A, Base.H);
        });
        // Test 7
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            instance.isBefore(Base.H, Base.A);
        });
    }

    /**
     * Test of setBefore method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testSetBefore() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        // Test 1
        try {
            instance.setBefore(Base.C, Base.A);
            assertTrue(instance.isBefore(Base.C, Base.A));
        } catch (Exception ignore) {
        }
        // Test 2
        Assertions.assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.A, Base.C);
        });
        // Test 3
        Assertions.assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.A, Base.D);
            instance.setBefore(Base.D, Base.C);
        });
        // Test 4
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            instance.setBefore(Base.A, Base.H);
        });
        // Test 5
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            instance.setBefore(Base.H, Base.A);
        });
    }

    /**
     * Test of size method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testSize() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        int expResult = 7;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testIsEmpty() {
        // Test 1
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // Test 2
        instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testContains() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>();
        // Test 1
        boolean expResult = false;
        Object o = null;
        boolean result = instance.contains(o);
        assertEquals(expResult, result);
        // Test 2
        expResult = false;
        result = instance.contains(Base.C);
        assertEquals(expResult, result);

        instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        // Test 3
        expResult = false;
        result = instance.contains(o);
        assertEquals(expResult, result);
        // Test 4
        expResult = true;
        result = instance.contains(Base.C);
        assertEquals(expResult, result);
        // Test 5
        expResult = false;
        result = instance.contains(Base.H);
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testIterator() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        HashSet<Base> used = new HashSet<>();
        Iterator<Base> iterator = instance.iterator();
        assertTrue(iterator.hasNext());
        Base next = iterator.next();
        iterator.remove();
        assertFalse(used.contains(next));
        used.add(next);
        assertTrue(iterator.hasNext());
        next = iterator.next();
        iterator.remove();
        assertFalse(used.contains(next));
        used.add(next);
        assertTrue(iterator.hasNext());
        next = iterator.next();
        iterator.remove();
        assertFalse(used.contains(next));
        used.add(next);
        assertTrue(iterator.hasNext());
        next = iterator.next();
        iterator.remove();
        assertFalse(used.contains(next));
        used.add(next);
        assertTrue(iterator.hasNext());
        next = iterator.next();
        iterator.remove();
        assertFalse(used.contains(next));
        used.add(next);
        assertTrue(iterator.hasNext());
        next = iterator.next();
        iterator.remove();
        assertFalse(used.contains(next));
        used.add(next);
        assertTrue(iterator.hasNext());
        next = iterator.next();
        iterator.remove();
        assertFalse(used.contains(next));
        used.add(next);
        assertFalse(iterator.hasNext());
        assertEquals(new HashDirectedAcyclicGraph<Base>(), instance);
    }

    /**
     * Test of toArray method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testToArray_0args() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>(Base.A, Base.D, Base.H, Base.C);
        HashDirectedAcyclicGraph<Base> duplicate = new HashDirectedAcyclicGraph<Base>();
        Object[] result = instance.toArray();
        assertEquals(4, result.length);
        for (Object obj : result) {
            assertTrue(instance.contains((Base) obj));
            assertTrue(duplicate.add((Base) obj));
        }
        assertEquals(instance, duplicate);
    }

    /**
     * Test of toArray method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testToArray_null() {
        Base[] prototype = new Base[0];
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>(Base.A, Base.D, Base.H, Base.C);
        HashDirectedAcyclicGraph<Base> duplicate = new HashDirectedAcyclicGraph<Base>();
        Object[] result = instance.toArray(prototype);
        assertEquals(4, result.length);
        for (Object obj : result) {
            assertTrue(instance.contains((Base) obj));
            assertTrue(duplicate.add((Base) obj));
        }
        assertEquals(instance, duplicate);
    }

    /**
     * Test of add method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testAdd() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>();
        assertTrue(instance.isEmpty());
        assertTrue(instance.add(Base.B));
        assertTrue(instance.add(Base.D));
        assertTrue(instance.add(Base.H));
        assertFalse(instance.add(Base.H));
        assertTrue(instance.contains(Base.B));
        assertTrue(instance.contains(Base.D));
        assertTrue(instance.contains(Base.H));
        assertFalse(instance.contains(Base.A));
        assertFalse(instance.contains(Base.C));
        assertFalse(instance.contains(Base.E));
        assertFalse(instance.contains(Base.F));
        assertFalse(instance.contains(Base.G));
        Assertions.assertThrows(NullPointerException.class, () -> instance.add(null));
    }

    /**
     * Test of remove method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testRemove() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        assertTrue(instance.remove(Base.B));
        assertTrue(instance.remove(Base.D));
        assertFalse(instance.remove(Base.D));
        assertFalse(instance.remove(Base.H));
        assertFalse(instance.contains(Base.B));
        assertFalse(instance.contains(Base.D));
        assertFalse(instance.contains(Base.H));
        assertTrue(instance.contains(Base.A));
        assertTrue(instance.contains(Base.C));
        assertTrue(instance.contains(Base.E));
        assertTrue(instance.contains(Base.F));
        assertTrue(instance.contains(Base.G));
    }

    /**
     * Test of containsAll method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testContainsAll() {
        Collection<Base> collection = new HashSet<>();
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        // Test 1
        assertTrue(instance.containsAll(collection));
        // Test 2
        collection.add(Base.B);
        collection.add(Base.D);
        collection.add(Base.E);
        assertTrue(instance.containsAll(collection));
        // Test 3
        collection.add(Base.H);
        assertFalse(instance.containsAll(collection));
    }

    /**
     * Test of addAll method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testAddAll() {
        // Test 1
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C);
        assertTrue(instance.addAll(new HashDirectedAcyclicGraph<>(Base.G, Base.H)));
        assertFalse(instance.addAll(new HashDirectedAcyclicGraph<>(Base.A, Base.B)));
        assertEquals(instance, new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.G, Base.H));
    }

    /**
     * Test of removeAll method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testRemoveAll() {
        // Test 1
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.G);
        assertTrue(instance.removeAll(new HashDirectedAcyclicGraph<>(Base.G, Base.H)));
        assertEquals(instance, new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C));
        // Test 2
        instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.G);
        assertFalse(instance.removeAll(new HashDirectedAcyclicGraph<>(Base.H)));
    }

    /**
     * Test of retainAll method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testRetainAll() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.D);
        assertTrue(instance.retainAll(new HashDirectedAcyclicGraph<>(Base.B, Base.C, Base.G, Base.H)));
        assertEquals(new HashDirectedAcyclicGraph<>(Base.B, Base.C), instance);
    }

    /**
     * Test of clear method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testClear() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        instance.clear();
        assertTrue(instance.isEmpty());
    }

    /**
     * Test for getSortedList,class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    @SneakyThrows
    public void test_getSortedList() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        instance.setBefore(Base.A, Base.B);//A -> B
        instance.setBefore(Base.D, Base.B);//D -> B
        instance.setBefore(Base.E, Base.B);//E -> B
        instance.setBefore(Base.F, Base.B);//F -> B
        instance.setBefore(Base.G, Base.B);//G -> B
        instance.setBefore(Base.B, Base.C);//B -> C
        // ADEFG -> B -> C
        List<Base> sortedList = instance.getSortedList();
        Assertions.assertEquals(sortedList.get(sortedList.size() - 1), Base.C);
        Assertions.assertEquals(sortedList.get(sortedList.size() - 2), Base.B);
    }

    enum Base {A, B, C, D, E, F, G, H}
}
