package org.allaymc.api.permission.tree;

import java.util.List;

/**
 * Represents a node in a permission tree.
 *
 * @author daoge_cmd
 */
public interface PermissionNode {

    /**
     * Gets the name of this node.
     *
     * @return the name of this node.
     */
    String getName();

    /**
     * Gets the full name of this node.
     *
     * @return the full name of this node.
     */
    String getFullName();

    /**
     * Checks if the given node name can match this node.
     *
     * @param nodeName the node name to check.
     *
     * @return {@code true} if the given node name can match this node, {@code false} otherwise.
     */
    boolean canMatch(String nodeName);

    /**
     * Gets the parent of this node.
     *
     * @return the parent of this node.
     */
    PermissionNode getParent();

    /**
     * Adds a leaf to this node.
     *
     * @param nodeName the name of the leaf to add.
     *
     * @return the leaf node.
     */
    PermissionNode addLeaf(String nodeName);

    /**
     * Checks if this node is a leaf.
     *
     * @return {@code true} if this node is a leaf, {@code false} otherwise.
     */
    boolean isLeaf();

    /**
     * Checks if this node is the root node.
     *
     * @return {@code true} if this node is the root node, {@code false} otherwise.
     */
    default boolean isRoot() {
        return false;
    }

    /**
     * Gets the leaves of this node.
     *
     * @return the leaves of this node.
     */
    List<PermissionNode> getLeaves();
}
