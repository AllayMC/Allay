package org.allaymc.server.datastruct.dag;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code HashDirectedAcyclicGraphTest} checks precedence queries, topological ordering,
 * and inherited set behavior using enum member fixtures.
 * @author Cool_Loong
 */
public class HashDirectedAcyclicGraphTest {
    /**
     * {@code setABCDEFG} holds seven isolated fixture members prepared before the suite.
     * Each test constructs its own graph so edge mutations do not change this shared seed
     * graph.
     */
    static HashDirectedAcyclicGraph<Base> setABCDEFG;

    /**
     * Creates the shared graph of seven isolated enum members before test execution.
     */
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
     * Adds several direct predecessors and a following edge around one member. The
     * immediate-predecessor result must match those direct edges, and self or reverse
     * edges that close a cycle must be rejected.
     */
    @Test
    public void testGetBefore() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
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
            // This setup suppresses a cycle failure, leaving the following assertions to expose
            // whether the expected relationships were actually established.
        }

        assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.B, Base.B);
        });
        assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.C, Base.B);
        });
    }

    /**
     * Builds direct and transitive precedence relationships among fixture members.
     * Reachability must distinguish forward, reverse, self, unrelated, and absent-node
     * queries according to the tested graph behavior.
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
            // This setup suppresses failures, so the later behavior assertions provide the only
            // evidence that the requested fixture relationships were established.
        }

        assertTrue(instance.isBefore(Base.A, Base.B));
        assertFalse(instance.isBefore(Base.B, Base.A));
        assertFalse(instance.isBefore(Base.A, Base.A));
        assertFalse(instance.isBefore(Base.A, Base.D));
        assertFalse(instance.isBefore(Base.D, Base.A));
        assertFalse(instance.isBefore(Base.D, Base.D));
        assertTrue(instance.isBefore(Base.A, Base.C));
        assertThrows(IllegalArgumentException.class, () -> instance.isBefore(Base.A, Base.H));
        assertThrows(IllegalArgumentException.class, () -> instance.isBefore(Base.H, Base.A));
    }

    /**
     * Adds a precedence edge and then attempts several conflicting or absent-member edges.
     * Valid ordering must remain discoverable, while cycles and missing endpoints must
     * raise their expected exceptions.
     */
    @Test
    public void testSetBefore() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        try {
            instance.setBefore(Base.C, Base.A);
            assertTrue(instance.isBefore(Base.C, Base.A));
        } catch (Exception ignore) {
            // This setup suppresses failures, so the later behavior assertions provide the only
            // evidence that the requested fixture relationships were established.
        }
        assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.A, Base.C);
        });
        assertThrows(DAGCycleException.class, () -> {
            instance.setBefore(Base.A, Base.D);
            instance.setBefore(Base.D, Base.C);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setBefore(Base.A, Base.H);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setBefore(Base.H, Base.A);
        });
    }

    /**
     * Copies the shared seven-member seed into a fresh graph. Its size must count every
     * distinct fixture member, protecting the graph's inherited set membership count.
     */
    @Test
    public void testSize() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        assertEquals(7, instance.size());
    }

    /**
     * Checks a new graph before and after construction from the nonempty seed. The empty
     * flag must distinguish those states using ordinary set membership.
     */
    @Test
    public void testIsEmpty() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<Base>();
        assertTrue(instance.isEmpty());
        instance = new HashDirectedAcyclicGraph<Base>(setABCDEFG);
        assertFalse(instance.isEmpty());
    }

    /**
     * Checks missing, present, and {@code null} member lookups across empty and populated graphs.
     * Membership must reflect the stored enum fixtures without treating {@code null} as present.
     */
    @Test
    public void testContains() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>();
        assertFalse(instance.contains(null));
        assertFalse(instance.contains(Base.C));

        instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        assertFalse(instance.contains(null));
        assertTrue(instance.contains(Base.C));
        assertFalse(instance.contains(Base.H));
    }

    /**
     * Iterates over all seven seed members and removes each through the iterator. Every
     * returned member must be unique, and the inherited set view must be empty at the end.
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
     * Converts a four-member graph to an object array. Each returned element must belong
     * to the source exactly once, protecting array membership without assuming hash
     * iteration order.
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
     * Converts a four-member graph using an empty typed prototype array. Every returned
     * member must belong to the graph exactly once, protecting typed array conversion
     * independently of iteration order.
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
     * Adds distinct and repeated enum members to an empty graph. Return values and
     * membership must reflect set semantics, while a {@code null} insertion must throw the
     * expected exception.
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
     * Removes isolated members from a copied seed graph and repeats selected removals.
     * Membership and return values must distinguish removed, already absent, and
     * never-present members.
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
     * Checks empty, partial-present, and partly absent member collections against the seed
     * graph. The result must require every supplied member while accepting an empty
     * request.
     */
    @Test
    public void testContainsAll() {
        Collection<Base> collection = new HashSet<>();
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        assertTrue(instance.containsAll(collection));
        collection.add(Base.B);
        collection.add(Base.D);
        collection.add(Base.E);
        assertTrue(instance.containsAll(collection));
        collection.add(Base.H);
        assertFalse(instance.containsAll(collection));
    }

    /**
     * Adds a collection containing new members and then one containing only existing
     * members. Return values and final membership must distinguish actual additions from
     * duplicate-only requests.
     */
    @Test
    public void testAddAll() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C);
        assertTrue(instance.addAll(new HashDirectedAcyclicGraph<>(Base.G, Base.H)));
        assertFalse(instance.addAll(new HashDirectedAcyclicGraph<>(Base.A, Base.B)));
        assertEquals(new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.G, Base.H), instance);
    }

    /**
     * Removes collections containing present and absent members from fresh fixture graphs.
     * The assertions protect resulting set membership and whether any matching member was
     * removed.
     */
    @Test
    public void testRemoveAll() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.G);
        assertTrue(instance.removeAll(new HashDirectedAcyclicGraph<>(Base.G, Base.H)));
        assertEquals(new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C), instance);
        instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.G);
        assertFalse(instance.removeAll(new HashDirectedAcyclicGraph<>(Base.H)));
    }

    /**
     * Retains a subset of members from a four-member graph. The remaining inherited set
     * membership must equal the requested intersection, excluding requested members that
     * were absent initially.
     */
    @Test
    public void testRetainAll() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(Base.A, Base.B, Base.C, Base.D);
        assertTrue(instance.retainAll(new HashDirectedAcyclicGraph<>(Base.B, Base.C, Base.G, Base.H)));
        assertEquals(new HashDirectedAcyclicGraph<>(Base.B, Base.C), instance);
    }

    /**
     * Clears a graph copied from the populated seed fixture. The inherited set view must
     * report empty afterward; this case does not query adjacency bookkeeping after
     * clearing.
     */
    @Test
    public void testClear() {
        HashDirectedAcyclicGraph<Base> instance = new HashDirectedAcyclicGraph<>(setABCDEFG);
        instance.clear();
        assertTrue(instance.isEmpty());
    }

    /**
     * Builds several roots preceding one shared member and a final follower. The last two
     * output positions must satisfy those dependencies, and a repeated sort must preserve
     * the recorded order.
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
        List<Base> sortedList = instance.getSortedList();
        assertEquals(Base.C, sortedList.getLast());
        assertEquals(Base.B, sortedList.get(sortedList.size() - 2));
        // Sorting consumes copied edges, so the second call must still observe the graph
        // relationships checked by the first traversal.
        assertEquals(sortedList, instance.getSortedList());
    }

    /**
     * {@code Base} identifies stable enum members used to construct the graph fixtures.
     */
    enum Base {
        /** First graph fixture member. */ A,
        /** Second graph fixture member. */ B,
        /** Third graph fixture member. */ C,
        /** Fourth graph fixture member. */ D,
        /** Fifth graph fixture member. */ E,
        /** Sixth graph fixture member. */ F,
        /** Seventh graph fixture member. */ G,
        /** Absent or additional fixture member. */ H
    }
}
