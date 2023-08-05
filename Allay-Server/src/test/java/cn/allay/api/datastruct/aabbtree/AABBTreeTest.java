package cn.allay.api.datastruct.aabbtree;

import org.joml.primitives.AABBf;
import org.joml.primitives.Rayf;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static cn.allay.api.datastruct.aabbtree.AABBTreeNode.INVALID_NODE_INDEX;
import static org.junit.jupiter.api.Assertions.*;

public class AABBTreeTest {
   @Test
   public void shouldAddAnObjectToTheTree() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);

      // When
      tree.add(entity);

      // Then
      assertTrue(tree.contains(entity));
   }

   @Test
   public void shouldNotAddTheSameObjectTwice() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);

      // When
      tree.add(entity);
      tree.add(entity);

      // Then
      assertEquals(1, tree.size());
      assertEquals(1, tree.getNodes().size());
      assertEquals(0, tree.getRoot());
      assertTrue(tree.contains(entity));
   }

   @Test
   public void shouldAddTwoObjectsToTheTreeByCreatingABranch() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);

      // When
      tree.add(entity1);
      tree.add(entity2);

      // Then
      assertEquals(3, tree.getNodes().size());
      assertEquals(2, tree.getRoot());
      assertFalse(tree.getNodes().get(2).isLeaf());
      assertTrue(tree.getNodes().get(1).isLeaf());
      assertTrue(tree.getNodes().get(0).isLeaf());
   }

   @Test
   public void shouldCorrectlyCalculateHeights() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);

      // When
      tree.add(entity1);
      tree.add(entity2);

      // Then
      List<AABBTreeNode<TestEntity>> nodes = tree.getNodes();
      assertEquals(1, nodes.get(2).getHeight());
      assertEquals(0, nodes.get(1).getHeight());
      assertEquals(0, nodes.get(0).getHeight());
   }

   @Test
   public void shouldCorrectlyCalculateHeightsInAMoreComplexCase() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity3 = new TestEntity(3, 5.0f, 0.0f, 10.0f, 10.0f);

      // When
      tree.add(entity1);
      tree.add(entity2);
      tree.add(entity3);

      // Then
      List<AABBTreeNode<TestEntity>> nodes = tree.getNodes();
      AABBTreeNode<TestEntity> root = nodes.get(tree.getRoot());

      AABBTreeNode<TestEntity> rootRight = nodes.get(root.getRightChild());
      assertEquals(2, root.getHeight());
      assertEquals(0, nodes.get(root.getLeftChild()).getHeight());
      assertEquals(1, rootRight.getHeight());
      assertEquals(0, nodes.get(rootRight.getLeftChild()).getHeight());
      assertEquals(0, nodes.get(rootRight.getRightChild()).getHeight());
   }

   @Test
   public void shouldCorrectlyCalculateHeightsInAnEvenMoreComplexCase() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity3 = new TestEntity(3, 5.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity4 = new TestEntity(4, 15.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity5 = new TestEntity(5, -25.0f, 10.0f, 10.0f, 10.0f);

      // When
      tree.add(entity1);
      tree.add(entity2);
      tree.add(entity3);
      tree.add(entity4);
      tree.add(entity5);

      // Then
      List<AABBTreeNode<TestEntity>> nodes = tree.getNodes();
      AABBTreeNode<TestEntity> root = nodes.get(tree.getRoot());
      AABBTreeNode<TestEntity> rootLeft = nodes.get(root.getLeftChild());
      AABBTreeNode<TestEntity> rootRight = nodes.get(root.getRightChild());

      // TODO Verify that everything is fine
      assertEquals(3, root.getHeight());
      assertEquals(2, rootLeft.getHeight());
      assertEquals(1, nodes.get(rootLeft.getLeftChild()).getHeight());
      assertEquals(0, nodes.get(rootLeft.getRightChild()).getHeight());
      assertEquals(1, rootRight.getHeight());
      assertEquals(0, nodes.get(rootRight.getLeftChild()).getHeight());
      assertEquals(0, nodes.get(rootRight.getRightChild()).getHeight());
   }

   @Test
   public void shouldCorrectlyCalculateHeightsAfterRemovingAnObject() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity3 = new TestEntity(3, 5.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity1);
      tree.add(entity2);
      tree.add(entity3);

      // When
      tree.remove(entity2);

      // Then
      List<AABBTreeNode<TestEntity>> nodes = tree.getNodes();
      AABBTreeNode<TestEntity> root = nodes.get(tree.getRoot());

      assertEquals(1, root.getHeight());
      assertEquals(0, nodes.get(root.getLeftChild()).getHeight());
      assertEquals(0, nodes.get(root.getRightChild()).getHeight());
   }

   @Test
   public void shouldRemoveAnObjectFromTheTree() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity);

      // When
      tree.remove(entity);

      // Then
      assertFalse(tree.contains(entity));
   }

   @Test
   public void shouldRemovingAnObjectMarkANodeFree() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity);

      // When
      tree.remove(entity);

      // Then
      assertFalse(tree.getFreeNodes().isEmpty());
      assertEquals(0, (int) tree.getFreeNodes().peek());
   }

   @Test
   public void shouldRemovingAnObjectInAMoreComplexTreeMarkNodesFree() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity1);
      tree.add(entity2);

      // When
      tree.remove(entity1);

      // Then
      assertFalse(tree.getFreeNodes().isEmpty());
      assertEquals(2, tree.getFreeNodes().size());
      assertEquals(1, tree.getRoot());
   }

   @Test
   public void shouldContainsReturnTrueIfAnObjectIsAddedToTheTree() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);

      // When
      tree.add(entity1);

      // Then
      assertTrue(tree.contains(entity1));
      assertFalse(tree.contains(entity2));
   }

   @Test
   public void shouldClearTheTree() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity1);

      // When
      tree.clear();

      // Then
      assertFalse(tree.contains(entity1));
      assertFalse(tree.contains(entity2));
      assertTrue(tree.getFreeNodes().isEmpty());
      assertTrue(tree.getNodes().isEmpty());
      assertEquals(INVALID_NODE_INDEX, tree.getRoot());
   }

   @Test
   public void shouldNotDetectAABBOverlaps() {
      // Given
      AABBf testForOverlap = new AABBf(1.0f, 10.5f, 0.0f, 2.0f, 10.0f, 0.0f);
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity1);
      tree.add(entity2);

      // When
      List<TestEntity> result = new ArrayList<>();
      tree.detectOverlaps(testForOverlap, result);

      // Then
      assertTrue(result.isEmpty());
   }

   @Test
   public void shouldDetectAABBOverlaps() {
      // Given
      AABBf testForOverlap = new AABBf(1.0f, 5.1f, 0.0f, 2.0f, 10.0f, 0.0f);
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, -20.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity1);
      tree.add(entity2);

      // When
      List<TestEntity> result = new ArrayList<>();
      tree.detectOverlaps(testForOverlap, result);

      // Then
      assertEquals(1, result.size());
      assertEquals(2, result.get(0).getId());
   }

   @Test
   public void shouldDetectAABBOverlapsWithFiltering() {
      // Given
      AABBf testForOverlap = new AABBf(1.0f, 5.1f, 0.0f, 2.0f, 10.0f, 0.0f);
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity1);
      tree.add(entity2);

      // When
      List<TestEntity> result = new ArrayList<>();
      List<TestEntity> filteredResult = new ArrayList<>();
      tree.detectOverlaps(testForOverlap, result);
      tree.detectOverlaps(testForOverlap, e -> e.getId() != 2, filteredResult);

      // Then
      assertEquals(2, result.size());
      assertEquals(1, filteredResult.size());
      assertEquals(1, filteredResult.get(0).getId());
   }

   @Test
   public void shouldDetectCollidingPairs() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity3 = new TestEntity(3, 11.0f, 0.0f, 10.0f, 10.0f);
      tree.add(entity1);
      tree.add(entity2);
      tree.add(entity3);
      CollisionPair<TestEntity> givenPair = new CollisionPair<>(entity2, entity1);

      // When
      List<CollisionPair<TestEntity>> pairs = new ArrayList<>();
      tree.detectCollisionPairs(pairs);

      // Then
      assertEquals(1, pairs.size());
      CollisionPair<TestEntity> collisionPair = pairs.get(0);
      assertEquals(givenPair, collisionPair);
   }

   @Test
   public void shouldDetectCollidingPairsWithFiltering() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      TestEntity entity1 = new TestEntity(1, 0.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity3 = new TestEntity(3, 11.0f, 0.0f, 10.0f, 10.0f);
      TestEntity entity4 = new TestEntity(4, -6.0f, 0.0f, 3.0f, 1.0f);
      tree.add(entity1);
      tree.add(entity2);
      tree.add(entity3);
      tree.add(entity4);
      CollisionPair<TestEntity> givenPair = new CollisionPair<>(entity2, entity4);

      // When
      List<CollisionPair<TestEntity>> pairs = new ArrayList<>();
      List<CollisionPair<TestEntity>> filteredPairs = new ArrayList<>();
      tree.detectCollisionPairs(pairs);
      tree.detectCollisionPairs((a, b) -> a.getId() == 2 && b.getId() == 4, filteredPairs);

      // Then
      assertEquals(2, pairs.size());
      assertEquals(1, filteredPairs.size());
      CollisionPair<TestEntity> filteredCollisionPair = filteredPairs.get(0);
      assertEquals(givenPair, filteredCollisionPair);
   }

   @Test
   public void shouldDetectRayIntersection() {
      // Given
      AABBTree<TestEntity> tree = givenTree();
      Rayf ray = new Rayf(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
      TestEntity entity1 = new TestEntity(1, 2.0f, 0.0f, 3.0f, 3.0f);
      TestEntity entity2 = new TestEntity(2, -5.0f, 0.0f, 3.0f, 2.0f);
      tree.add(entity1);
      tree.add(entity2);

      // When
      List<TestEntity> intersecting = new ArrayList<>();
      tree.detectRayIntersection(ray, intersecting);

      // Then
      assertEquals(1, intersecting.size());
      assertEquals(1L, intersecting.get(0).getId());
   }

   @Test
   public void shouldRestoreNodeToInitialStateOnReuse() {
      // Given
      TestEntity entity = new TestEntity(1, 0.0f, 0.0f, 1.0f, 1.0f);
      AABBTreeNode<TestEntity> node = new AABBTreeNode<>();

      // When
      node.setHeight(10);
      node.setIndex(2);
      node.setParent(1);
      node.setData(entity);
      node.assignChildren(3, 4);

      node.resetForReuse();

      // Then
      assertEquals(INVALID_NODE_INDEX, node.getLeftChild());
      assertEquals(INVALID_NODE_INDEX, node.getRightChild());
      assertEquals(new AABBf(), node.getAABB());
      assertEquals(INVALID_NODE_INDEX, node.getParent());
      assertEquals(0, node.getHeight());
      assertNull(node.getData());
   }

   private AABBTree<TestEntity> givenTree() {
      return new AABBTree<>();
   }
}
