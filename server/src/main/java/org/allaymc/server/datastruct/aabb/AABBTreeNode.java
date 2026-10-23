package org.allaymc.server.datastruct.aabb;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.world.physics.HasAABB;
import org.joml.primitives.AABBd;

/**
 * {@code AABBTreeNode} stores one indexed leaf or branch of an AABB tree. Leaves retain an
 * object and its margin-expanded bounds, while branches refer to two child indices. Reuse
 * clears relationships and bounds while preserving the node's assigned storage index. The
 * generated accessors expose mutable bounds and child storage for the owning tree's
 * coordinated updates.
 * @author daoge_cmd
 */
@Getter
@Setter
public final class AABBTreeNode<E extends HasAABB> {
    /**
     * {@code INVALID_NODE_INDEX} marks an absent parent, child, root, or unassigned node
     * index. Tree operations test this sentinel before dereferencing node storage.
     */
    public static final int INVALID_NODE_INDEX = -1;

    /**
     * {@code LEFT_CHILD} selects the left entry in the child-index array. Branch
     * construction and rotations use this slot when rewiring tree relationships.
     */
    public static final int LEFT_CHILD = 0;
    /**
     * {@code RIGHT_CHILD} selects the right entry in the child-index array. Branch
     * construction and rotations use this slot when rewiring tree relationships.
     */
    public static final int RIGHT_CHILD = 1;

    /**
     * {@code MAX_NUM_OF_CHILDREN_PER_NODE} fixes branch capacity at two child indices,
     * matching the binary insertion, rotation, and traversal algorithms of the owning
     * tree.
     */
    private static final int MAX_NUM_OF_CHILDREN_PER_NODE = 2;

    /**
     * {@code children} stores left and right node indices. The generated getter returns
     * the live array, so external mutation must preserve the tree's reciprocal parent
     * links.
     */
    private final int[] children = new int[MAX_NUM_OF_CHILDREN_PER_NODE];
    /**
     * {@code aabb} holds the mutable bounds cached for this node. The generated getter
     * exposes that same object; leaf recomputation and branch unions update it in place.
     */
    private final AABBd aabb = new AABBd();

    /**
     * Parent node index or {@code INVALID_NODE_INDEX}.
     */
    private int parent = INVALID_NODE_INDEX;
    /**
     * {@code index} identifies this node's slot in tree storage. {@code resetForReuse()}
     * preserves it so a recycled node can return to the same slot.
     */
    private int index = INVALID_NODE_INDEX;
    /**
     * Cached subtree height above this node.
     */
    private int height;
    /**
     * {@code data} retains a leaf object or {@code null} for a reset or branch node. Reuse
     * drops this reference before another leaf is initialized.
     */
    private E data;

    /**
     * Creates an unassigned node whose two child indices initially indicate absence.
     */
    public AABBTreeNode() {
        assignChildren(INVALID_NODE_INDEX, INVALID_NODE_INDEX);
    }

    /**
     * Returns whether the left child slot contains {@code INVALID_NODE_INDEX}.
     */
    public boolean isLeaf() {
        return getLeftChild() == INVALID_NODE_INDEX;
    }

    /**
     * Replaces a matching left or right child index with {@code replacement}. An index
     * absent from both slots has no effect; callers update the replacement node's parent
     * link separately.
     */
    void replaceChild(int childIndexToReplace, int replacement) {
        if (getLeftChild() == childIndexToReplace) {
            assignChild(LEFT_CHILD, replacement);
        } else if (getRightChild() == childIndexToReplace) {
            assignChild(RIGHT_CHILD, replacement);
        }
    }

    /**
     * Stores one child index without updating that child's parent link. {@code whichChild}
     * must select the left or right slot, and the owning tree coordinates reciprocal links
     * separately.
     */
    void assignChild(int whichChild, int childIndex) {
        children[whichChild] = childIndex;
    }

    /**
     * Stores both supplied child indices without changing their parent links. The owning
     * tree establishes reciprocal parent relationships separately, and
     * {@code INVALID_NODE_INDEX} marks an absent child during initialization or reuse.
     */
    public void assignChildren(int childA, int childB) {
        assignChild(LEFT_CHILD, childA);
        assignChild(RIGHT_CHILD, childB);
    }

    /**
     * Recomputes leaf bounds from {@code data.getOffsetAABB()} and an equal margin on each
     * side. A {@code null} object leaves cached bounds unchanged; the margin uses the same
     * coordinate units as the object.
     */
    void computeAABBWithMargin(float margin) {
        if (data == null) return;
        var offsetAABB = data.getOffsetAABB();
        // Expand cached leaf bounds so the tree queries the configured broad-phase margin
        // around the object's current offset box.
        aabb.setMin(offsetAABB.minX() - margin, offsetAABB.minY() - margin, offsetAABB.minZ() - margin);
        aabb.setMax(offsetAABB.maxX() + margin, offsetAABB.maxY() + margin, offsetAABB.maxZ() + margin);
    }

    /**
     * Clears child links, parent, height, and retained leaf data for recycling. The cached
     * bounds become an empty infinite range, while {@code index} is preserved to keep the
     * same storage slot.
     */
    public void resetForReuse() {
        assignChildren(INVALID_NODE_INDEX, INVALID_NODE_INDEX);

        // Reset bounds to the empty union identity while retaining the storage index required
        // when this pooled node is reused.
        aabb.setMin(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        aabb.setMax(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

        this.parent = INVALID_NODE_INDEX;
        this.height = 0;
        this.data = null;
    }

    /**
     * Returns the node index stored in the left child slot. A result of
     * {@code INVALID_NODE_INDEX} denotes an absent child, and callers must check that
     * sentinel before dereferencing tree storage.
     */
    public int getLeftChild() {
        return children[LEFT_CHILD];
    }

    /**
     * Returns the node index stored in the right child slot. A result of
     * {@code INVALID_NODE_INDEX} denotes an absent child, and callers must check that
     * sentinel before dereferencing tree storage.
     */
    public int getRightChild() {
        return children[RIGHT_CHILD];
    }
}
