package org.allaymc.server.datastruct.aabbtree;

import org.allaymc.server.datastruct.aabb.AABBTree;
import org.allaymc.server.datastruct.aabb.AABBTreeNode;
import org.allaymc.server.datastruct.aabb.CollisionPair;
import org.joml.primitives.AABBd;
import org.joml.primitives.Rayf;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.allaymc.server.datastruct.aabb.AABBTreeNode.INVALID_NODE_INDEX;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code AABBTreeTest} exercises indexed tree structure, node reuse, and geometric query
 * results with deterministic box fixtures.
 * @author daoge_cmd
 */
public class AABBTreeTest {
    /**
     * Adds one identified box to an empty tree. Identifier lookup must then report that
     * object as present, protecting the basic insertion and object-map association.
     */
    @Test
    public void shouldAddAnObjectToTheTree() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);

        tree.add(entity);

        assertTrue(tree.contains(entity));
    }

    /**
     * Adds the same object twice through the update-on-duplicate path. The tree must
     * retain one object and one root leaf without allocating an additional active branch.
     */
    @Test
    public void shouldNotAddTheSameObjectTwice() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);

        tree.add(entity);
        tree.add(entity);

        assertEquals(1, tree.size());
        assertEquals(1, tree.getNodes().size());
        assertEquals(0, tree.getRoot());
        assertTrue(tree.contains(entity));
    }

    /**
     * Adds two boxes to an initially empty tree. A branch must become the root with two
     * leaf children, protecting the indexed structure created for the second insertion.
     */
    @Test
    public void shouldAddTwoObjectsToTheTreeByCreatingABranch() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);

        tree.add(entity1);
        tree.add(entity2);

        assertEquals(3, tree.getNodes().size());
        assertEquals(2, tree.getRoot());
        assertFalse(tree.getNodes().get(2).isLeaf());
        assertTrue(tree.getNodes().get(1).isLeaf());
        assertTrue(tree.getNodes().get(0).isLeaf());
    }

    /**
     * Adds two boxes beneath one root branch. The branch height must be one and both leaf
     * heights zero, protecting initial cached subtree-height calculation.
     */
    @Test
    public void shouldCorrectlyCalculateHeights() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);

        tree.add(entity1);
        tree.add(entity2);

        List<AABBTreeNode<TestEntity>> nodes = tree.getNodes();
        assertEquals(1, nodes.get(2).getHeight());
        assertEquals(0, nodes.get(1).getHeight());
        assertEquals(0, nodes.get(0).getHeight());
    }

    /**
     * Adds three boxes whose insertion produces a deeper right subtree. The root and
     * descendant height assertions protect cached height propagation through that specific
     * branch arrangement.
     */
    @Test
    public void shouldCorrectlyCalculateHeightsInAMoreComplexCase() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        TestEntity entity3 = new TestEntity(3, 5.0, 0.0, 10.0, 10.0);

        tree.add(entity1);
        tree.add(entity2);
        tree.add(entity3);

        List<AABBTreeNode<TestEntity>> nodes = tree.getNodes();
        AABBTreeNode<TestEntity> root = nodes.get(tree.getRoot());

        AABBTreeNode<TestEntity> rootRight = nodes.get(root.getRightChild());
        assertEquals(2, root.getHeight());
        assertEquals(0, nodes.get(root.getLeftChild()).getHeight());
        assertEquals(1, rootRight.getHeight());
        assertEquals(0, nodes.get(rootRight.getLeftChild()).getHeight());
        assertEquals(0, nodes.get(rootRight.getRightChild()).getHeight());
    }

    /**
     * Adds five boxes distributed on both sides of the initial geometry. The expected
     * branch and leaf heights protect the resulting balanced shape for this deterministic
     * insertion sequence.
     */
    @Test
    public void shouldCorrectlyCalculateHeightsInAnEvenMoreComplexCase() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        TestEntity entity3 = new TestEntity(3, 5.0, 0.0, 10.0, 10.0);
        TestEntity entity4 = new TestEntity(4, 15.0, 0.0, 10.0, 10.0);
        TestEntity entity5 = new TestEntity(5, -25.0, 10.0, 10.0, 10.0);

        tree.add(entity1);
        tree.add(entity2);
        tree.add(entity3);
        tree.add(entity4);
        tree.add(entity5);

        List<AABBTreeNode<TestEntity>> nodes = tree.getNodes();
        AABBTreeNode<TestEntity> root = nodes.get(tree.getRoot());
        AABBTreeNode<TestEntity> rootLeft = nodes.get(root.getLeftChild());
        AABBTreeNode<TestEntity> rootRight = nodes.get(root.getRightChild());

        assertEquals(3, root.getHeight());
        assertEquals(2, rootLeft.getHeight());
        assertEquals(1, nodes.get(rootLeft.getLeftChild()).getHeight());
        assertEquals(0, nodes.get(rootLeft.getRightChild()).getHeight());
        assertEquals(1, rootRight.getHeight());
        assertEquals(0, nodes.get(rootRight.getLeftChild()).getHeight());
        assertEquals(0, nodes.get(rootRight.getRightChild()).getHeight());
    }

    /**
     * Removes one box from a three-object tree. The remaining root must have height one
     * and two zero-height leaves, protecting the tested sibling-promotion removal path.
     */
    @Test
    public void shouldCorrectlyCalculateHeightsAfterRemovingAnObject() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        TestEntity entity3 = new TestEntity(3, 5.0, 0.0, 10.0, 10.0);
        tree.add(entity1);
        tree.add(entity2);
        tree.add(entity3);

        tree.remove(entity2);

        List<AABBTreeNode<TestEntity>> nodes = tree.getNodes();
        AABBTreeNode<TestEntity> root = nodes.get(tree.getRoot());

        assertEquals(1, root.getHeight());
        assertEquals(0, nodes.get(root.getLeftChild()).getHeight());
        assertEquals(0, nodes.get(root.getRightChild()).getHeight());
    }

    /**
     * Removes the sole object from a previously populated tree. Identifier lookup must
     * then report it absent, protecting deletion of an entry that also served as the root.
     */
    @Test
    public void shouldRemoveAnObjectFromTheTree() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        tree.add(entity);

        tree.remove(entity);

        assertFalse(tree.contains(entity));
    }

    /**
     * Removes the sole object from a tree with one allocated leaf. Its storage index must
     * enter the free-node deque, protecting reuse availability after root removal.
     */
    @Test
    public void shouldRemovingAnObjectMarkANodeFree() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        tree.add(entity);

        tree.remove(entity);

        assertFalse(tree.getFreeNodes().isEmpty());
        assertEquals(0, (int) tree.getFreeNodes().peek());
    }

    /**
     * Removes one object from a two-leaf tree. Both the removed leaf and branch must
     * become reusable while the surviving sibling becomes the new root.
     */
    @Test
    public void shouldRemovingAnObjectInAMoreComplexTreeMarkNodesFree() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        tree.add(entity1);
        tree.add(entity2);

        tree.remove(entity1);

        assertFalse(tree.getFreeNodes().isEmpty());
        assertEquals(2, tree.getFreeNodes().size());
        assertEquals(1, tree.getRoot());
    }

    /**
     * Adds one of two distinct identified boxes to a tree. Lookup must distinguish the
     * inserted identifier from the absent identifier, protecting membership checks
     * independently of box overlap.
     */
    @Test
    public void shouldContainsReturnTrueIfAnObjectIsAddedToTheTree() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);

        tree.add(entity1);

        assertTrue(tree.contains(entity1));
        assertFalse(tree.contains(entity2));
    }

    /**
     * Clears a tree after inserting one object. Object lookup, allocated nodes, reusable
     * slots, and the root sentinel must all reflect an empty tree afterward.
     */
    @Test
    public void shouldClearTheTree() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        tree.add(entity1);

        tree.clear();

        assertFalse(tree.contains(entity1));
        assertFalse(tree.contains(entity2));
        assertTrue(tree.getFreeNodes().isEmpty());
        assertTrue(tree.getNodes().isEmpty());
        assertEquals(INVALID_NODE_INDEX, tree.getRoot());
    }

    /**
     * Queries a tree with a box whose supplied vertical range does not overlap the fixture
     * bounds. The result must remain empty for this deliberately supplied query geometry.
     */
    @Test
    public void shouldNotDetectAABBOverlaps() {
        AABBd testForOverlap = new AABBd(1.0, 10.5, 0.0, 2.0, 10.0, 0.0);
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        tree.add(entity1);
        tree.add(entity2);

        List<TestEntity> result = new ArrayList<>();
        tree.detectOverlaps(testForOverlap, result);

        assertTrue(result.isEmpty());
    }

    /**
     * Queries two spatially separated fixtures with a box intersecting only one. The
     * result must contain that object's identifier exactly once, protecting live-tree
     * overlap pruning.
     */
    @Test
    public void shouldDetectAABBOverlaps() {
        AABBd testForOverlap = new AABBd(1.0, 5.1, 0.0, 2.0, 10.0, 0.0);
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, -20.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        tree.add(entity1);
        tree.add(entity2);

        List<TestEntity> result = new ArrayList<>();
        tree.detectOverlaps(testForOverlap, result);

        assertEquals(1, result.size());
        assertEquals(2, result.getFirst().getLongId());
    }

    /**
     * Queries two overlapping fixtures both with and without an identifier predicate. The
     * filtered result must exclude the rejected identifier while retaining the other
     * geometric match.
     */
    @Test
    public void shouldDetectAABBOverlapsWithFiltering() {
        AABBd testForOverlap = new AABBd(1.0, 5.1, 0.0, 2.0, 10.0, 0.0);
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        tree.add(entity1);
        tree.add(entity2);

        List<TestEntity> result = new ArrayList<>();
        List<TestEntity> filteredResult = new ArrayList<>();
        tree.detectOverlaps(testForOverlap, result);
        tree.detectOverlaps(testForOverlap, e -> e.getLongId() != 2, filteredResult);

        assertEquals(2, result.size());
        assertEquals(1, filteredResult.size());
        assertEquals(1, filteredResult.getFirst().getLongId());
    }

    /**
     * Queries a three-object tree with one overlapping object pair. The result must
     * contain one unordered identifier pair, protecting duplicate suppression across
     * opposite traversal directions.
     */
    @Test
    public void shouldDetectCollidingPairs() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        TestEntity entity3 = new TestEntity(3, 11.0, 0.0, 10.0, 10.0);
        tree.add(entity1);
        tree.add(entity2);
        tree.add(entity3);
        CollisionPair<TestEntity> givenPair = new CollisionPair<>(entity2, entity1);

        List<CollisionPair<TestEntity>> pairs = new ArrayList<>();
        tree.detectCollisionPairs(pairs);

        assertEquals(1, pairs.size());
        CollisionPair<TestEntity> collisionPair = pairs.getFirst();
        assertEquals(givenPair, collisionPair);
    }

    /**
     * Queries four fixtures that produce two overlapping pairs before filtering. An
     * ordered endpoint predicate must retain the expected pair, protecting predicate
     * application before pair acceptance.
     */
    @Test
    public void shouldDetectCollidingPairsWithFiltering() {
        AABBTree<TestEntity> tree = givenTree();
        TestEntity entity1 = new TestEntity(1, 0.0, 0.0, 10.0, 10.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 10.0, 10.0);
        TestEntity entity3 = new TestEntity(3, 11.0, 0.0, 10.0, 10.0);
        TestEntity entity4 = new TestEntity(4, -6.0, 0.0, 3.0, 1.0);
        tree.add(entity1);
        tree.add(entity2);
        tree.add(entity3);
        tree.add(entity4);
        CollisionPair<TestEntity> givenPair = new CollisionPair<>(entity2, entity4);

        List<CollisionPair<TestEntity>> pairs = new ArrayList<>();
        List<CollisionPair<TestEntity>> filteredPairs = new ArrayList<>();
        tree.detectCollisionPairs(pairs);
        tree.detectCollisionPairs((a, b) -> a.getLongId() == 2 && b.getLongId() == 4, filteredPairs);

        assertEquals(2, pairs.size());
        assertEquals(1, filteredPairs.size());
        CollisionPair<TestEntity> filteredCollisionPair = filteredPairs.getFirst();
        assertEquals(givenPair, filteredCollisionPair);
    }

    /**
     * Tests a diagonal ray against two boxes on different sides of its origin. Only the
     * box crossed by the forward ray must appear in the result.
     */
    @Test
    public void shouldDetectRayIntersection() {
        AABBTree<TestEntity> tree = givenTree();
        Rayf ray = new Rayf(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
        TestEntity entity1 = new TestEntity(1, 2.0, 0.0, 3.0, 3.0);
        TestEntity entity2 = new TestEntity(2, -5.0, 0.0, 3.0, 2.0);
        tree.add(entity1);
        tree.add(entity2);

        List<TestEntity> intersecting = new ArrayList<>();
        tree.detectRayIntersection(ray, intersecting);

        assertEquals(1, intersecting.size());
        assertEquals(1L, intersecting.getFirst().getLongId());
    }

    /**
     * Populates a node's links, height, and data before resetting it. The assertions
     * protect empty bounds, absent relationships, zero height, and released data for later
     * slot reuse.
     */
    @Test
    public void shouldRestoreNodeToInitialStateOnReuse() {
        TestEntity entity = new TestEntity(1, 0.0, 0.0, 1.0, 1.0);
        AABBTreeNode<TestEntity> node = new AABBTreeNode<>();

        node.setHeight(10);
        node.setIndex(2);
        node.setParent(1);
        node.setData(entity);
        node.assignChildren(3, 4);

        node.resetForReuse();

        assertEquals(INVALID_NODE_INDEX, node.getLeftChild());
        assertEquals(INVALID_NODE_INDEX, node.getRightChild());
        assertEquals(new AABBd(), node.getAabb());
        assertEquals(INVALID_NODE_INDEX, node.getParent());
        assertEquals(0, node.getHeight());
        assertNull(node.getData());
    }

    /**
     * Returns an empty tree using the default area-growth insertion heuristic.
     */
    private AABBTree<TestEntity> givenTree() {
        return new AABBTree<>();
    }
}
