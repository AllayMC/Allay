package org.allaymc.server.datastruct.dag;

import lombok.SneakyThrows;
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
        assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.B, Base.B);
        });
        // Test 3
        assertThrows(DAGCycleException.class, () -> {
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
        assertTrue(instance.isBefore(Base.A, Base.B));
        // Test 2
        assertFalse(instance.isBefore(Base.B, Base.A));
        // Test 3
        assertFalse(instance.isBefore(Base.A, Base.A));
        // Test 4
        assertFalse(instance.isBefore(Base.A, Base.D));
        // Test 5
        assertFalse(instance.isBefore(Base.D, Base.A));
        // Test 6
        assertFalse(instance.isBefore(Base.D, Base.D));
        // Test 5
        assertTrue(instance.isBefore(Base.A, Base.C));
        // Test 6
        assertThrows(IllegalArgumentException.class, () -> instance.isBefore(Base.A, Base.H));
        // Test 7
        assertThrows(IllegalArgumentException.class, () -> instance.isBefore(Base.H, Base.A));
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
        assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.A, Base.C);
        });
        // Test 3
        assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.A, Base.D);
            instance.setBefore(Base.D, Base.C);
        });
        // Test 4
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setBefore(Base.A, Base.H);
        });
        // Test 5
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setBefore(Base.H, Base.A);
        });
    }

    /**
     * Test of size method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testSize() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        assertEquals(7, instance.size());
    }

    /**
     * Test of isEmpty method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testIsEmpty() {
        // Test 1
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>();
        assertTrue(instance.isEmpty());
        // Test 2
        instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        assertFalse(instance.isEmpty());
    }

    /**
     * Test of contains method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testContains() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>();
        // Test 1
        assertFalse(instance.contains(null));
        // Test 2
        assertFalse(instance.contains(Base.C));

        instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        // Test 3
        assertFalse(instance.contains(null));
        // Test 4
        assertTrue(instance.contains(Base.C));
        // Test 5
        assertFalse(instance.contains(Base.H));
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
    public void testToArrayZeroArgs() {
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
    public void testToArrayNull() {
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
        assertThrows(NullPointerException.class, () -> instance.add(null));
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
        assertEquals(new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.G, Base.H), instance);
    }

    /**
     * Test of removeAll method, of class HashDirectedAcyclicGraph<Base>.
     */
    @Test
    public void testRemoveAll() {
        // Test 1
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.G);
        assertTrue(instance.removeAll(new HashDirectedAcyclicGraph<>(Base.G, Base.H)));
        assertEquals(new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C), instance);
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
    public void testGetSortedList() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        instance.setBefore(Base.A, Base.B);//A -> B
        instance.setBefore(Base.D, Base.B);//D -> B
        instance.setBefore(Base.E, Base.B);//E -> B
        instance.setBefore(Base.F, Base.B);//F -> B
        instance.setBefore(Base.G, Base.B);//G -> B
        instance.setBefore(Base.B, Base.C);//B -> C
        // ADEFG -> B -> C
        List<Base> sortedList = instance.getSortedList();
        assertEquals(Base.C, sortedList.getLast());
        assertEquals(Base.B, sortedList.get(sortedList.size() - 2));
    }

    enum Base {A, B, C, D, E, F, G, H}
}
