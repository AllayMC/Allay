package org.allaymc.server.datastruct.aabb;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Getter;
import org.allaymc.api.world.physics.AABBOverlapFilter;
import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;
import org.joml.FrustumIntersection;
import org.joml.Matrix4fc;
import org.joml.RayAabIntersection;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.joml.primitives.Rayfc;

import java.util.*;
import java.util.function.Predicate;

import static java.lang.Math.max;

/**
 * @author daoge_cmd
 */
@Getter
public final class AABBTree<T extends HasAABB & HasLongId> {

    public static final float DEFAULT_FAT_AABB_MARGIN = 0f;

    private final List<AABBTreeNode<T>> nodes;
    private final AABBTreeHeuristicFunction<T> insertionHeuristicFunction;
    private final AABBOverlapFilter<T> defaultAABBOverlapFilter;
    private final CollisionFilter<T> defaultCollisionFilter;
    private final Map<AABBTreeObject<T>, Integer> objects;
    private final Deque<Integer> freeNodes;
    private final FrustumIntersection frustumIntersection;
    private final RayAabIntersection rayIntersection;
    private final float fatAABBMargin;
    @Getter
    private int root;

    public AABBTree() {
        this(new AreaAABBHeuristicFunction<>(), DEFAULT_FAT_AABB_MARGIN);
    }

    public AABBTree(AABBTreeHeuristicFunction<T> insertionHeuristicFunction, float fatAABBMargin) {
        nodes = new ObjectArrayList<>();
        root = AABBTreeNode.INVALID_NODE_INDEX;
        this.insertionHeuristicFunction = insertionHeuristicFunction;
        if (this.insertionHeuristicFunction == null) {
            throw new IllegalArgumentException("A valid insertion heuristic function is required");
        }
        objects = new HashMap<>();
        freeNodes = new ArrayDeque<>();
        defaultAABBOverlapFilter = new DefaultAABBOverlapFilter<>();
        defaultCollisionFilter = new DefaultCollisionFilter<>();
        this.fatAABBMargin = fatAABBMargin;

        frustumIntersection = new FrustumIntersection();
        rayIntersection = new RayAabIntersection();
    }

    private AABBTreeNode<T> allocateNode() {
        if (freeNodes.isEmpty()) {
            return new AABBTreeNode<>();
        }
        Integer freeIndex = freeNodes.pop();

        AABBTreeNode<T> aabbTreeNode = nodes.get(freeIndex);
        aabbTreeNode.resetForReuse();
        return aabbTreeNode;
    }

    private void deallocateNode(AABBTreeNode<T> node) {
        freeNodes.offer(node.getIndex());
    }

    private int addNodeAndGetIndex(AABBTreeNode<T> node) {
        if (node.getIndex() != AABBTreeNode.INVALID_NODE_INDEX) {
            return node.getIndex();
        }
        nodes.add(node);
        int idx = nodes.size() - 1;
        node.setIndex(idx);
        return idx;
    }

    private AABBTreeNode<T> createLeafNode(T object) {
        AABBTreeNode<T> leafNode = allocateNode();
        leafNode.setData(object);
        leafNode.computeAABBWithMargin(fatAABBMargin);
        return leafNode;
    }

    private void moveNodeToParent(AABBTreeNode<T> node, int newParentIndex) {
        int oldParentIndex = node.getParent();
        if (oldParentIndex != AABBTreeNode.INVALID_NODE_INDEX) {
            getNodeAt(oldParentIndex).replaceChild(node.getIndex(), newParentIndex);
        }
        node.setParent(newParentIndex);
    }

    private AABBTreeNode<T> createBranchNode(AABBTreeNode<T> childA, AABBTreeNode<T> childB) {
        AABBTreeNode<T> branchNode = allocateNode();
        branchNode.assignChildren(childA.getIndex(), childB.getIndex());

        int branchNodeIndex = addNodeAndGetIndex(branchNode);
        moveNodeToParent(childA, branchNodeIndex);
        moveNodeToParent(childB, branchNodeIndex);

        return branchNode;
    }

    private AABBTreeNode<T> getNodeAt(int index) {
        return nodes.get(index);
    }

    private AABBTreeNode<T> balanceRight(AABBTreeNode<T> node, AABBTreeNode<T> left, AABBTreeNode<T> right) {
        AABBTreeNode<T> rightLeftChild = getNodeAt(right.getLeftChild());
        AABBTreeNode<T> rightRightChild = getNodeAt(right.getRightChild());

        right.assignChild(AABBTreeNode.LEFT_CHILD, node.getIndex());
        right.setParent(node.getParent());
        node.setParent(right.getIndex());

        if (right.getParent() != AABBTreeNode.INVALID_NODE_INDEX) {
            AABBTreeNode<T> rightParent = getNodeAt(right.getParent());
            if (rightParent.getLeftChild() == node.getIndex()) {
                rightParent.assignChild(AABBTreeNode.LEFT_CHILD, right.getIndex());
            } else {
                rightParent.assignChild(AABBTreeNode.RIGHT_CHILD, right.getIndex());
            }
        } else {
            root = right.getIndex();
        }

        if (rightLeftChild.getHeight() > rightRightChild.getHeight()) {
            right.assignChild(AABBTreeNode.RIGHT_CHILD, rightLeftChild.getIndex());
            node.assignChild(AABBTreeNode.RIGHT_CHILD, rightRightChild.getIndex());
            rightRightChild.setParent(node.getIndex());
            left.getAabb().union(rightRightChild.getAabb(), node.getAabb());
            node.getAabb().union(rightLeftChild.getAabb(), right.getAabb());
            node.setHeight(1 + max(left.getHeight(), rightRightChild.getHeight()));
            right.setHeight(1 + max(node.getHeight(), rightLeftChild.getHeight()));
        } else {
            right.assignChild(AABBTreeNode.RIGHT_CHILD, rightRightChild.getIndex());
            node.assignChild(AABBTreeNode.RIGHT_CHILD, rightLeftChild.getIndex());
            rightLeftChild.setParent(node.getIndex());
            left.getAabb().union(rightLeftChild.getAabb(), node.getAabb());
            node.getAabb().union(rightRightChild.getAabb(), right.getAabb());
            node.setHeight(1 + max(left.getHeight(), rightLeftChild.getHeight()));
            right.setHeight(1 + max(node.getHeight(), rightRightChild.getHeight()));
        }

        return right;
    }

    private AABBTreeNode<T> balanceLeft(AABBTreeNode<T> node, AABBTreeNode<T> left, AABBTreeNode<T> right) {
        AABBTreeNode<T> leftLeftChild = getNodeAt(left.getLeftChild());
        AABBTreeNode<T> leftRightChild = getNodeAt(left.getRightChild());

        left.assignChild(AABBTreeNode.LEFT_CHILD, node.getIndex());
        left.setParent(node.getParent());
        node.setParent(left.getIndex());

        if (left.getParent() != AABBTreeNode.INVALID_NODE_INDEX) {
            AABBTreeNode<T> leftParent = getNodeAt(left.getParent());
            if (leftParent.getLeftChild() == node.getIndex()) {
                leftParent.assignChild(AABBTreeNode.LEFT_CHILD, left.getIndex());
            } else {
                leftParent.assignChild(AABBTreeNode.RIGHT_CHILD, left.getIndex());
            }
        } else {
            root = left.getIndex();
        }

        if (leftLeftChild.getHeight() > leftRightChild.getHeight()) {
            left.assignChild(AABBTreeNode.RIGHT_CHILD, leftLeftChild.getIndex());
            node.assignChild(AABBTreeNode.LEFT_CHILD, leftRightChild.getIndex());
            leftRightChild.setParent(node.getIndex());
            right.getAabb().union(leftRightChild.getAabb(), node.getAabb());
            node.getAabb().union(leftLeftChild.getAabb(), left.getAabb());
            node.setHeight(1 + max(right.getHeight(), leftRightChild.getHeight()));
            left.setHeight(1 + max(node.getHeight(), leftLeftChild.getHeight()));
        } else {
            left.assignChild(AABBTreeNode.RIGHT_CHILD, leftRightChild.getIndex());
            node.assignChild(AABBTreeNode.LEFT_CHILD, leftLeftChild.getIndex());
            leftLeftChild.setParent(node.getIndex());
            right.getAabb().union(leftLeftChild.getAabb(), node.getAabb());
            node.getAabb().union(leftRightChild.getAabb(), left.getAabb());
            node.setHeight(1 + max(right.getHeight(), leftLeftChild.getHeight()));
            left.setHeight(1 + max(node.getHeight(), leftRightChild.getHeight()));
        }

        return left;
    }

    private AABBTreeNode<T> balanceNode(AABBTreeNode<T> node) {
        if (node.isLeaf() || node.getHeight() < 2) {
            return node;
        }

        AABBTreeNode<T> left = getNodeAt(node.getLeftChild());
        AABBTreeNode<T> right = getNodeAt(node.getRightChild());

        int balance = right.getHeight() - left.getHeight();

        if (balance > 1) {
            return balanceRight(node, left, right);
        }
        if (balance < -1) {
            return balanceLeft(node, left, right);
        }
        return node;
    }

    private void syncUpHierarchy(AABBTreeNode<T> startingPoint) {
        AABBTreeNode<T> node = startingPoint.getParent() == AABBTreeNode.INVALID_NODE_INDEX ? null : getNodeAt(startingPoint.getParent());
        while (node != null && node.getIndex() != AABBTreeNode.INVALID_NODE_INDEX) {
            node = balanceNode(node);

            AABBTreeNode<T> left = getNodeAt(node.getLeftChild());
            AABBTreeNode<T> right = getNodeAt(node.getRightChild());

            node.setHeight(1 + max(left.getHeight(), right.getHeight()));
            left.getAabb().union(right.getAabb(), node.getAabb());

            node = node.getParent() == AABBTreeNode.INVALID_NODE_INDEX ? null : getNodeAt(node.getParent());
        }
    }

    private void insertNode(int node) {
        AABBTreeNode<T> nodeToAdd = getNodeAt(node);
        AABBTreeNode<T> parentNode = getNodeAt(root);

        AABBd nodeToAddAABB = nodeToAdd.getAabb();

        while (!parentNode.isLeaf()) {
            AABBTreeNode<T> leftChild = getNodeAt(parentNode.getLeftChild());
            AABBTreeNode<T> rightChild = getNodeAt(parentNode.getRightChild());
            AABBd aabbA = leftChild.getAabb();
            AABBd aabbB = rightChild.getAabb();

            AABBTreeHeuristicFunction.HeuristicResult heuristicResult = insertionHeuristicFunction
                    .getInsertionHeuristic(aabbA, aabbB, nodeToAdd.getData(), nodeToAddAABB);
            parentNode = AABBTreeHeuristicFunction.HeuristicResult.LEFT.equals(heuristicResult) ? leftChild : rightChild;
        }

        int oldParentIndex = parentNode.getParent();
        AABBTreeNode<T> newParent = createBranchNode(nodeToAdd, parentNode);
        newParent.setParent(oldParentIndex);
        newParent.setHeight(parentNode.getHeight() + 1);

        if (oldParentIndex == AABBTreeNode.INVALID_NODE_INDEX) {
            root = newParent.getIndex();
            newParent.setParent(AABBTreeNode.INVALID_NODE_INDEX);
        }

        syncUpHierarchy(nodeToAdd);
    }

    private void detectCollisionPairsWithNode(AABBTreeNode<T> nodeToTest, CollisionFilter<T> filter, Set<CollisionPair<T>> alreadyTested,
                                              List<CollisionPair<T>> result) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offer(root);
        AABBd overlapWith = nodeToTest.getAabb();

        while (!stack.isEmpty()) {
            int nodeIndex = stack.pop();
            if (nodeIndex == AABBTreeNode.INVALID_NODE_INDEX) {
                continue;
            }

            AABBTreeNode<T> node = getNodeAt(nodeIndex);
            AABBd nodeAABB = node.getAabb();
            if (nodeAABB.intersectsAABB(overlapWith)) {
                if (node.isLeaf() && node.getIndex() != nodeToTest.getIndex()) {
                    T nodeData = node.getData();
                    T testedData = nodeToTest.getData();
                    CollisionPair<T> collisionPair = new CollisionPair<>(testedData, nodeData);
                    if (filter.test(testedData, nodeData) && !alreadyTested.contains(collisionPair)) {
                        alreadyTested.add(collisionPair);
                        result.add(collisionPair);
                    }
                } else {
                    stack.offer(node.getLeftChild());
                    stack.offer(node.getRightChild());
                }
            }
        }
    }

    public void add(T object) {
        if (contains(object)) {
            update(object);
            return;
        }

        AABBTreeNode<T> leafNode = createLeafNode(object);

        int newNodeIndex = addNodeAndGetIndex(leafNode);
        if (root == AABBTreeNode.INVALID_NODE_INDEX) {
            root = newNodeIndex;
        } else {
            insertNode(newNodeIndex);
        }

        objects.put(AABBTreeObject.create(object), newNodeIndex);
    }

    public void clear() {
        nodes.clear();
        objects.clear();
        freeNodes.clear();
        root = AABBTreeNode.INVALID_NODE_INDEX;
    }

    public void update(T object) {
        if (!contains(object)) {
            add(object);
            return;
        }

        remove(object);
        add(object);
    }

    public void remove(T object) {
        Integer objectNodeIndex = objects.remove(AABBTreeObject.create(object));
        if (root == AABBTreeNode.INVALID_NODE_INDEX || objectNodeIndex == null) {
            return;
        }

        if (objectNodeIndex == root) {
            deallocateNode(getNodeAt(objectNodeIndex));
            root = AABBTreeNode.INVALID_NODE_INDEX;
            return;
        }

        AABBTreeNode<T> node = getNodeAt(objectNodeIndex);
        AABBTreeNode<T> nodeParent = node.getParent() == AABBTreeNode.INVALID_NODE_INDEX ? null : getNodeAt(node.getParent());
        AABBTreeNode<T> nodeGrandparent = nodeParent == null || nodeParent.getParent() == AABBTreeNode.INVALID_NODE_INDEX ? null : getNodeAt(nodeParent.getParent());
        AABBTreeNode<T> nodeSibling = null;
        if (nodeParent != null) {
            nodeSibling = nodeParent.getLeftChild() == objectNodeIndex ? getNodeAt(nodeParent.getRightChild()) : getNodeAt(nodeParent.getLeftChild());
            deallocateNode(nodeParent);
        }
        deallocateNode(node);

        if (nodeGrandparent == null) {
            root = nodeSibling.getIndex();
            nodeSibling.setParent(AABBTreeNode.INVALID_NODE_INDEX);
            return;
        }

        if (nodeGrandparent.getLeftChild() == nodeParent.getIndex()) {
            nodeGrandparent.assignChild(AABBTreeNode.LEFT_CHILD, nodeSibling.getIndex());
        } else {
            nodeGrandparent.assignChild(AABBTreeNode.RIGHT_CHILD, nodeSibling.getIndex());
        }

        nodeSibling.setParent(nodeGrandparent.getIndex());
        syncUpHierarchy(nodeGrandparent);
    }

    public void detectOverlaps(AABBdc overlapWith, List<T> result) {
        detectOverlaps(overlapWith, defaultAABBOverlapFilter, result);
    }

    public void detectOverlaps(AABBdc overlapWith, AABBOverlapFilter<T> filter, List<T> result) {
        traverseTree(aabb -> aabb.intersectsAABB(overlapWith), filter, result);
    }

    public void detectCollisionPairs(List<CollisionPair<T>> result) {
        detectCollisionPairs(defaultCollisionFilter, result);
    }

    public void detectCollisionPairs(CollisionFilter<T> filter, List<CollisionPair<T>> result) {
        result.clear();
        if (root == AABBTreeNode.INVALID_NODE_INDEX) {
            return;
        }

        Set<CollisionPair<T>> alreadyTested = new HashSet<>();

        for (AABBTreeNode<T> testedNode : nodes) {
            if (!testedNode.isLeaf()) {
                continue;
            }

            detectCollisionPairsWithNode(testedNode, filter, alreadyTested, result);
        }
    }

    public void detectInFrustum(Matrix4fc worldViewProjection, List<T> result) {
        detectInFrustum(worldViewProjection, defaultAABBOverlapFilter, result);
    }

    public void detectInFrustum(Matrix4fc worldViewProjection, AABBOverlapFilter<T> filter, List<T> result) {
        frustumIntersection.set(worldViewProjection, false);
        traverseTree(aabb -> frustumIntersection.testAab((float) aabb.minX(), (float) aabb.minY(), (float) aabb.minZ(), (float) aabb.maxX(), (float) aabb.maxY(), (float) aabb.maxZ()), filter, result);
    }

    public void detectRayIntersection(Rayfc ray, List<T> result) {
        detectRayIntersection(ray, defaultAABBOverlapFilter, result);
    }

    // TODO: Use Rayfc here
    public void detectRayIntersection(Rayfc ray, AABBOverlapFilter<T> filter, List<T> result) {
        rayIntersection.set(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ());
        traverseTree(aabb -> rayIntersection.test((float) aabb.minX(), (float) aabb.minY(), (float) aabb.minZ(), (float) aabb.maxX(), (float) aabb.maxY(), (float) aabb.maxZ()), filter, result);
    }

    private void traverseTree(Predicate<AABBdc> nodeTest, AABBOverlapFilter<T> filter, List<T> result) {
        result.clear();
        if (root == AABBTreeNode.INVALID_NODE_INDEX) {
            return;
        }

        int[] stack = new int[nodes.size()];
        int stackSize = 0;
        stack[stackSize++] = root;

        while (stackSize > 0) {
            int nodeIndex = stack[--stackSize];
            if (nodeIndex == AABBTreeNode.INVALID_NODE_INDEX) continue;

            AABBTreeNode<T> node = getNodeAt(nodeIndex);
            AABBd nodeAABB = node.getAabb();
            if (nodeTest.test(nodeAABB)) {
                if (node.isLeaf()) {
                    T nodeData = node.getData();
                    if (filter.test(nodeData)) {
                        result.add(nodeData);
                    }
                } else {
                    stack[stackSize++] = node.getRightChild();
                    stack[stackSize++] = node.getLeftChild();
                }
            }
        }
    }

    public boolean contains(T object) {
        return objects.containsKey(AABBTreeObject.create(object));
    }

    public int size() {
        return objects.size();
    }
}
