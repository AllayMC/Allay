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
 * {@code AABBTree} indexes identified objects by cached axis-aligned bounds for overlap,
 * collision-pair, ray, and frustum queries. Leaves hold object bounds expanded by the
 * configured margin, while branches enclose their descendants. Identifier-based keys allow
 * another object instance with the same long identifier to update or remove a stored
 * entry; identifiers must remain stable while indexed.
 *
 * <p>
 * Insertion chooses a sibling through the configured heuristic, adds a parent branch, and
 * updates ancestor bounds and heights while balancing. Removal reconnects a sibling and
 * recycles detached node slots. Query traversal prunes branches using cached bounds, so
 * callers must update moving objects explicitly. Recycled nodes remain in allocated
 * storage until reused, which matters to operations that scan that storage rather than
 * following live links.
 *
 * <p>
 * {@code nodes}, {@code objects}, and {@code freeNodes} describe one mutable index and
 * must be coordinated together. Generated getters expose those structures and mutable node
 * bounds directly. The tree also reuses ray and frustum helpers between queries; callers
 * must serialize operations and avoid reentrant callbacks that modify or query the same
 * tree. Query methods clear caller-supplied result lists before appending matches.
 * @author daoge_cmd
 */
@Getter
public final class AABBTree<T extends HasAABB & HasLongId> {

    /**
     * {@code DEFAULT_FAT_AABB_MARGIN} supplies zero expansion to the default constructor.
     * When a leaf is created or refreshed, this margin adds no padding around the object's
     * offset box observed at that time.
     */
    public static final float DEFAULT_FAT_AABB_MARGIN = 0f;

    /**
     * {@code nodes} owns allocated tree slots, including detached slots awaiting reuse.
     * Generated access exposes this live list, so external edits can invalidate every
     * stored node index.
     */
    private final List<AABBTreeNode<T>> nodes;
    /**
     * {@code insertionHeuristicFunction} selects a child while locating insertion
     * siblings. The constructor requires a non-null implementation, and callbacks must
     * leave supplied tree boxes unchanged.
     */
    private final AABBTreeHeuristicFunction<T> insertionHeuristicFunction;
    /**
     * Predicate accepting every object for queries without an explicit overlap filter.
     */
    private final AABBOverlapFilter<T> defaultAABBOverlapFilter;
    /**
     * Predicate accepting every pair for queries without an explicit collision filter.
     */
    private final CollisionFilter<T> defaultCollisionFilter;
    /**
     * {@code objects} maps stable long identifiers to live leaf slots. Its keys retain
     * objects, and changing an object's identifier while indexed can prevent lookup or
     * removal.
     */
    private final Map<AABBTreeObject<T>, Integer> objects;
    /**
     * {@code freeNodes} contains detached node indices available for recycling. Entries
     * still address {@code nodes}; allocation resets the stored node before returning it
     * to active use.
     */
    private final Deque<Integer> freeNodes;
    /**
     * {@code frustumIntersection} holds mutable frustum planes for the current query.
     * Calls using this shared helper must not overlap or reenter the same tree.
     */
    private final FrustumIntersection frustumIntersection;
    /**
     * {@code rayIntersection} holds mutable ray state for the current query. Calls using
     * this shared helper must not overlap or reenter the same tree.
     */
    private final RayAabIntersection rayIntersection;
    /**
     * Coordinate margin added to each side of cached leaf bounds.
     */
    private final float fatAABBMargin;
    /**
     * {@code root} identifies the current root slot or {@code INVALID_NODE_INDEX} when
     * empty. Insertions, rotations, removals, and clearing update this entry as tree links
     * change.
     */
    @Getter
    private int root;

    /**
     * Creates an empty tree using the area-growth heuristic and zero cached-box margin.
     * The tree retains mutable object references and requires serialized operations,
     * including ray and frustum queries that reuse internal helpers.
     */
    public AABBTree() {
        this(new AreaAABBHeuristicFunction<>(), DEFAULT_FAT_AABB_MARGIN);
    }

    /**
     * Creates an empty tree using the supplied heuristic and cached-box margin. A
     * {@code null} heuristic throws {@code IllegalArgumentException}; the margin is
     * applied in object coordinate units and is not range-validated.
     */
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

    /**
     * Returns a new node or a reset node from the free-slot deque. A recycled node retains
     * its storage index, so callers can reinitialize it without appending another entry to
     * {@code nodes}.
     */
    private AABBTreeNode<T> allocateNode() {
        if (freeNodes.isEmpty()) {
            return new AABBTreeNode<>();
        }
        // Reuse the same storage slot after resetting links and payload, preventing stale
        // relationships from becoming active in a new leaf.
        Integer freeIndex = freeNodes.pop();

        AABBTreeNode<T> aabbTreeNode = nodes.get(freeIndex);
        aabbTreeNode.resetForReuse();
        return aabbTreeNode;
    }

    /**
     * Marks a detached node slot available for a later allocation. The node is not reset
     * or removed from {@code nodes} here; callers must first remove live links to it and
     * avoid duplicate releases.
     */
    private void deallocateNode(AABBTreeNode<T> node) {
        // Keep the slot allocated until its next checkout; callers have already detached live
        // links before making this index reusable.
        freeNodes.offer(node.getIndex());
    }

    /**
     * Returns the node's existing storage index or appends an unassigned node and assigns
     * one. A recycled node therefore reuses its original slot instead of increasing
     * allocated storage.
     */
    private int addNodeAndGetIndex(AABBTreeNode<T> node) {
        if (node.getIndex() != AABBTreeNode.INVALID_NODE_INDEX) {
            return node.getIndex();
        }
        nodes.add(node);
        int idx = nodes.size() - 1;
        node.setIndex(idx);
        return idx;
    }

    /**
     * Returns a newly allocated or recycled leaf retaining {@code object}. The cached
     * bounds are recomputed from the object's offset box and configured margin; callers
     * attach the leaf to live tree links separately.
     */
    private AABBTreeNode<T> createLeafNode(T object) {
        AABBTreeNode<T> leafNode = allocateNode();
        leafNode.setData(object);
        leafNode.computeAABBWithMargin(fatAABBMargin);
        return leafNode;
    }

    /**
     * Replaces the old parent's reference to {@code node} with {@code newParentIndex},
     * then updates the node's parent. Callers must initialize the new parent's child slots
     * separately to establish both link directions.
     */
    private void moveNodeToParent(AABBTreeNode<T> node, int newParentIndex) {
        int oldParentIndex = node.getParent();
        if (oldParentIndex != AABBTreeNode.INVALID_NODE_INDEX) {
            getNodeAt(oldParentIndex).replaceChild(node.getIndex(), newParentIndex);
        }
        node.setParent(newParentIndex);
    }

    /**
     * Creates a branch containing the two supplied indexed children and reconnects their
     * parent links. Both children must already have storage indices, and the caller
     * establishes the returned branch's own parent and cached height.
     *
     * <p>
     * The branch obtains its storage index before either child is moved, so reciprocal
     * links can refer to a valid slot. Moving each child also replaces its old parent's
     * child reference where one exists. This helper leaves bounds synchronization to the
     * insertion path after the new relationships are complete.
     */
    private AABBTreeNode<T> createBranchNode(AABBTreeNode<T> childA, AABBTreeNode<T> childB) {
        AABBTreeNode<T> branchNode = allocateNode();
        branchNode.assignChildren(childA.getIndex(), childB.getIndex());

        // Assign the parent slot before rewiring children so every new parent reference can
        // resolve through the node list.
        int branchNodeIndex = addNodeAndGetIndex(branchNode);
        moveNodeToParent(childA, branchNodeIndex);
        moveNodeToParent(childB, branchNodeIndex);

        return branchNode;
    }

    /**
     * Returns the allocated node stored at the supplied slot index.
     */
    private AABBTreeNode<T> getNodeAt(int index) {
        return nodes.get(index);
    }

    /**
     * Rotates a right-heavy subtree and returns its replacement root node. The supplied
     * children must belong to {@code node}, and the right child must itself have two
     * children available for redistribution. Parent links, cached boxes, and heights are
     * updated together.
     *
     * <p>
     * The old parent, or the tree root when absent, first points at the promoted right
     * child. The taller grandchild remains under that promoted node while the other joins
     * the old root. Bounding unions and heights are then recalculated from the new child
     * relationships so later ancestor synchronization observes the rotated subtree.
     */
    private AABBTreeNode<T> balanceRight(AABBTreeNode<T> node, AABBTreeNode<T> left, AABBTreeNode<T> right) {
        AABBTreeNode<T> rightLeftChild = getNodeAt(right.getLeftChild());
        AABBTreeNode<T> rightRightChild = getNodeAt(right.getRightChild());

        right.assignChild(AABBTreeNode.LEFT_CHILD, node.getIndex());
        right.setParent(node.getParent());
        node.setParent(right.getIndex());

        // Redirect the former parent before redistributing grandchildren, keeping the promoted
        // node connected to the live hierarchy after this rotation.
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

    /**
     * Rotates a left-heavy subtree and returns its replacement root node. The supplied
     * children must belong to {@code node}, and the left child must itself have two
     * children available for redistribution. Parent links, cached boxes, and heights are
     * updated together.
     *
     * <p>
     * The old parent, or the tree root when absent, first points at the promoted left
     * child. The taller grandchild remains under that promoted node while the other joins
     * the old root. Bounding unions and heights are then recalculated from the new child
     * relationships so later ancestor synchronization observes the rotated subtree.
     */
    private AABBTreeNode<T> balanceLeft(AABBTreeNode<T> node, AABBTreeNode<T> left, AABBTreeNode<T> right) {
        AABBTreeNode<T> leftLeftChild = getNodeAt(left.getLeftChild());
        AABBTreeNode<T> leftRightChild = getNodeAt(left.getRightChild());

        left.assignChild(AABBTreeNode.LEFT_CHILD, node.getIndex());
        left.setParent(node.getParent());
        node.setParent(left.getIndex());

        // Redirect the former parent before redistributing grandchildren, keeping the promoted
        // node connected to the live hierarchy after this rotation.
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

    /**
     * Returns the current or rotated root of the supplied subtree after comparing child
     * heights. Leaves and heights below two have no deeper branch requiring rotation.
     * Larger branches compute the right-minus-left height difference and rotate only when
     * its magnitude exceeds one.
     *
     * <p>
     * A positive imbalance promotes the right child, while a negative imbalance promotes
     * the left child. The selected helper rewires links and recomputes affected cached
     * state before returning the new subtree root. Callers must continue ancestor
     * traversal from that returned node because the original node may now have a different
     * parent.
     */
    private AABBTreeNode<T> balanceNode(AABBTreeNode<T> node) {
        // A leaf or height-one branch has no deeper grandchild subtree that requires a
        // balancing rotation.
        if (node.isLeaf() || node.getHeight() < 2) {
            return node;
        }

        AABBTreeNode<T> left = getNodeAt(node.getLeftChild());
        AABBTreeNode<T> right = getNodeAt(node.getRightChild());

        int balance = right.getHeight() - left.getHeight();

        // A height difference beyond one selects the heavy side for rotation.
        if (balance > 1) {
            return balanceRight(node, left, right);
        }
        if (balance < -1) {
            return balanceLeft(node, left, right);
        }
        return node;
    }

    /**
     * Balances and recomputes ancestors above {@code startingPoint}, leaving that starting
     * node itself unchanged. Each visited branch obtains its height and enclosing box from
     * its current children after any rotation. Callers must initialize the starting node's
     * own cached state before relying on this traversal.
     *
     * <p>
     * Rotations can replace a subtree root, so traversal continues from the returned
     * node's parent rather than the original node's parent. This order propagates
     * completed child changes upward. The walk stops at the absent-parent sentinel and
     * assumes live branches have two valid child indices.
     */
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

    /**
     * Attaches an indexed leaf to a nonempty tree and updates its ancestors. The
     * configured heuristic descends existing branches until a sibling leaf is selected,
     * and a new branch becomes the parent of both leaves.
     *
     * <p>
     * The selected sibling's former parent is retained before link replacement, allowing
     * the new branch to occupy the same place in the hierarchy. A former root sibling
     * causes {@code root} to move to the new branch. Ancestor synchronization then
     * computes enclosing boxes and heights from the completed child links, preserving live
     * traversal paths.
     */
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

        // Preserve the old attachment point before branch creation replaces the selected
        // sibling's parent link with its newly allocated parent.
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

    /**
     * Appends accepted overlaps between {@code nodeToTest} and leaves reached from the
     * current root. Cached boxes, including configured margins, determine overlap, while
     * matching node indices exclude the tested leaf from pairing with itself.
     *
     * <p>
     * The shared {@code alreadyTested} set suppresses pairs discovered in reverse order
     * during other leaf scans. Pair equality uses stable long identifiers, so endpoint
     * identifiers must remain unchanged throughout detection. The caller supplies a valid
     * test leaf and owns clearing the result list; this helper only appends newly accepted
     * pairs.
     */
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
                        // Record acceptance before appending so later leaf scans can suppress
                        // the same unordered identifier pair encountered from the opposite
                        // endpoint.
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

    /**
     * Adds the object's current offset bounds, or updates an existing entry with the same
     * long identifier. The object and identifier are retained, and the identifier must
     * remain stable while indexed.
     *
     * <p>
     * The new leaf obtains a storage slot before insertion creates any parent links. Empty
     * trees adopt that leaf as their root, while nonempty trees attach it through the
     * insertion heuristic and synchronize ancestors. The identifier map is updated after
     * structural insertion, so callbacks and concurrent operations must not inspect an
     * intermediate state.
     */
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

        // Publish the identifier lookup after completing structural insertion, so successful
        // lookup refers to a fully attached leaf rather than an unfinished branch.
        objects.put(AABBTreeObject.create(object), newNodeIndex);
    }

    /**
     * Clears allocated nodes, identifier mappings, and reusable slots, then marks the tree
     * empty. Existing external references obtained through generated getters or retained
     * node objects do not become valid entries in the cleared tree.
     */
    public void clear() {
        nodes.clear();
        objects.clear();
        freeNodes.clear();
        root = AABBTreeNode.INVALID_NODE_INDEX;
    }

    /**
     * Replaces an existing identifier entry with the supplied object's current offset
     * bounds. An absent identifier is added instead, and a present entry is removed before
     * the replacement is inserted.
     *
     * <p>
     * The remove-and-add sequence rebuilds the leaf and its position in the tree rather
     * than comparing movement against the configured margin. Queries must not overlap this
     * operation because the object is temporarily absent between those steps. Long
     * identifiers must remain stable so the map can find and replace the intended entry.
     */
    public void update(T object) {
        if (!contains(object)) {
            add(object);
            return;
        }

        // Reinsert from the current offset bounds because an update can move the object to a
        // different insertion branch in the tree.
        remove(object);
        add(object);
    }

    /**
     * Removes the indexed entry matching the object's long identifier when present.
     * Missing entries have no effect, and removing the root leaf leaves an empty tree.
     * Detached leaf and parent slots enter the reuse deque while remaining allocated in
     * {@code nodes}.
     *
     * <p>
     * For a non-root leaf, its sibling replaces the removed parent beneath the
     * grandparent, or becomes the new root. Parent links are updated before the ancestor
     * walk. That walk begins above the supplied grandparent, so the grandparent's own
     * cached bounds are not recomputed by this call; callers must not infer a complete
     * bounds refresh from the helper name.
     */
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

        // Without a grandparent, the surviving sibling must become root before the removed
        // parent slot can be used by a later insertion.
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

        // Complete the sibling's reciprocal parent link before ancestor synchronization reads
        // the surviving hierarchy and potentially rotates branches.
        nodeSibling.setParent(nodeGrandparent.getIndex());
        syncUpHierarchy(nodeGrandparent);
    }

    /**
     * Replaces {@code result} with leaves whose cached bounds overlap or touch
     * {@code overlapWith}. The configured margin participates in those bounds, and the
     * overload accepting a filter additionally rejects objects whose predicate returns
     * {@code false}.
     */
    public void detectOverlaps(AABBdc overlapWith, List<T> result) {
        detectOverlaps(overlapWith, defaultAABBOverlapFilter, result);
    }

    /**
     * Replaces {@code result} with leaves whose cached bounds overlap or touch
     * {@code overlapWith}. The configured margin participates in those bounds, and the
     * overload accepting a filter additionally rejects objects whose predicate returns
     * {@code false}.
     */
    public void detectOverlaps(AABBdc overlapWith, AABBOverlapFilter<T> filter, List<T> result) {
        traverseTree(aabb -> aabb.intersectsAABB(overlapWith), filter, result);
    }

    /**
     * Replaces {@code result} with accepted unordered pairs whose cached bounds overlap.
     * The detection scans allocated leaf-shaped nodes, including detached leaves not yet
     * reused, then traverses live links for each candidate; results can therefore include
     * a retained removed object. Endpoint identifiers must remain stable during the query.
     */
    public void detectCollisionPairs(List<CollisionPair<T>> result) {
        detectCollisionPairs(defaultCollisionFilter, result);
    }

    /**
     * Replaces {@code result} with accepted unordered pairs whose cached bounds overlap.
     * Endpoint identifiers must remain stable during the query because pair equality
     * suppresses duplicate discoveries by long identifier.
     *
     * <p>
     * The method scans allocated leaf-shaped nodes, including detached leaves not yet
     * reused, and traverses live links for each candidate. A retained removed object can
     * therefore appear in results before its slot is recycled. The acceptance set records
     * each passing pair once, even when opposite leaf scans discover it again; filtering
     * still runs before that duplicate check.
     */
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

    /**
     * Replaces {@code result} with accepted leaves whose cached bounds pass the frustum
     * test. The query converts double bounds to floats and reuses shared frustum state, so
     * callers must serialize queries and avoid reentrant tree callbacks.
     */
    public void detectInFrustum(Matrix4fc worldViewProjection, List<T> result) {
        detectInFrustum(worldViewProjection, defaultAABBOverlapFilter, result);
    }

    /**
     * Replaces {@code result} with accepted leaves whose cached bounds pass the frustum
     * test. The query converts double bounds to floats and reuses shared frustum state, so
     * callers must serialize queries and avoid reentrant tree callbacks.
     */
    public void detectInFrustum(Matrix4fc worldViewProjection, AABBOverlapFilter<T> filter, List<T> result) {
        // The helper stores this query's planes, so overlapping or reentrant frustum queries
        // would replace the state used by the traversal callback.
        frustumIntersection.set(worldViewProjection, false);
        // Narrow cached double bounds to the float coordinates accepted by the frustum helper.
        traverseTree(aabb -> frustumIntersection.testAab((float) aabb.minX(), (float) aabb.minY(), (float) aabb.minZ(), (float) aabb.maxX(), (float) aabb.maxY(), (float) aabb.maxZ()), filter, result);
    }

    /**
     * Replaces {@code result} with accepted leaves whose cached bounds intersect the
     * supplied ray. Bounds are narrowed to floats for the reusable ray helper; callers
     * must serialize queries and avoid reentrant callbacks on this tree.
     */
    public void detectRayIntersection(Rayfc ray, List<T> result) {
        detectRayIntersection(ray, defaultAABBOverlapFilter, result);
    }

    /**
     * Replaces {@code result} with accepted leaves whose cached bounds intersect the
     * supplied ray. Bounds are narrowed to floats for the reusable ray helper; callers
     * must serialize queries and avoid reentrant callbacks on this tree.
     */
    public void detectRayIntersection(Rayfc ray, AABBOverlapFilter<T> filter, List<T> result) {
        // The helper stores this query's ray, so overlapping or reentrant ray queries would
        // replace the state used by the traversal callback.
        rayIntersection.set(ray.oX(), ray.oY(), ray.oZ(), ray.dX(), ray.dY(), ray.dZ());
        // Narrow cached double bounds to the float coordinates accepted by the ray helper.
        traverseTree(aabb -> rayIntersection.test((float) aabb.minX(), (float) aabb.minY(), (float) aabb.minZ(), (float) aabb.maxX(), (float) aabb.maxY(), (float) aabb.maxZ()), filter, result);
    }

    /**
     * Replaces {@code result} with live leaf objects passing both node and object
     * predicates. The node predicate controls branch pruning, so it must reject a branch
     * only when every enclosed leaf can be excluded.
     *
     * <p>
     * The traversal starts at {@code root} and follows child indices rather than scanning
     * allocated storage, ignoring detached reusable nodes. Right children are pushed
     * before left children so the left branch is visited first. The stack is sized from
     * allocated nodes, and callbacks must not change structure while that traversal is in
     * progress.
     */
    private void traverseTree(Predicate<AABBdc> nodeTest, AABBOverlapFilter<T> filter, List<T> result) {
        result.clear();
        if (root == AABBTreeNode.INVALID_NODE_INDEX) {
            return;
        }

        // Live child links exclude recycled slots, while allocated storage bounds the traversal
        // stack capacity for the current unchanged tree structure.
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

    /**
     * Returns whether this tree has an entry with the object's current long identifier.
     * The supplied instance can differ from the retained object; identifier mutation after
     * insertion can make its original entry unreachable through this lookup.
     */
    public boolean contains(T object) {
        return objects.containsKey(AABBTreeObject.create(object));
    }

    /**
     * Returns the number of live object identifiers currently stored in this tree.
     */
    public int size() {
        return objects.size();
    }
}
