package cn.allay.api.datastruct.aabbtree;

import lombok.Getter;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBf;

public final class AABBTreeNode<E extends HasAABB> {
    public static final int INVALID_NODE_INDEX = -1;
    public static final int LEFT_CHILD = 0;
    public static final int RIGHT_CHILD = 1;
    private static final int MAX_NUM_OF_CHILDREN_PER_NODE = 2;
    private final int[] children;
    private final AABBd aabb;

    @Getter
    private int parent;
    @Getter
    private int index;
    @Getter
    private int height;
    @Getter
    private E data;

    public AABBTreeNode() {
        children = new int[MAX_NUM_OF_CHILDREN_PER_NODE];
        assignChildren(INVALID_NODE_INDEX, INVALID_NODE_INDEX);

        aabb = new AABBd();
        parent = INVALID_NODE_INDEX;
        index = INVALID_NODE_INDEX;
    }

    public boolean isLeaf() {
        return getLeftChild() == INVALID_NODE_INDEX;
    }

    void replaceChild(int childIndexToReplace, int replacement) {
        if (getLeftChild() == childIndexToReplace) {
            assignChild(LEFT_CHILD, replacement);
        } else if (getRightChild() == childIndexToReplace) {
            assignChild(RIGHT_CHILD, replacement);
        }
    }

    void assignChild(int whichChild, int childIndex) {
        children[whichChild] = childIndex;
    }

    public void assignChildren(int childA, int childB) {
        assignChild(LEFT_CHILD, childA);
        assignChild(RIGHT_CHILD, childB);
    }

    void computeAABBWithMargin(double margin) {
        if (data == null) {
            return;
        }
        AABBd dataAABB = data.copyAABBTo(aabb);
        aabb.setMin(dataAABB.minX - margin, dataAABB.minY - margin, dataAABB.minZ - margin);
        aabb.setMax(dataAABB.maxX + margin, dataAABB.maxY + margin, dataAABB.maxZ + margin);
    }

    public void resetForReuse() {
        assignChildren(INVALID_NODE_INDEX, INVALID_NODE_INDEX);
        aabb.setMin(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        aabb.setMax(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        setParent(INVALID_NODE_INDEX);
        setHeight(0);
        setData(null);

        aabb.setMin(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        aabb.setMax(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    public int getLeftChild() {
        return children[LEFT_CHILD];
    }

    public int getRightChild() {
        return children[RIGHT_CHILD];
    }

    public AABBd getAABB() {
        return aabb;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setData(E data) {
        this.data = data;
    }
}
