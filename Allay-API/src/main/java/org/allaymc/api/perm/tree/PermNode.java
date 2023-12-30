package org.allaymc.api.perm.tree;

import java.util.List;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public interface PermNode {

    String WILDCARD = "*";

    String getName();

    String getFullName();

    boolean canMatch(String nodeName);

    PermNode getParent();

    PermNode addLeaf(String nodeName);

    boolean isLeaf();

    default boolean isWildcardNode() {
        return getName().equals(WILDCARD);
    }

    default boolean isRoot() {
        return false;
    }

    List<PermNode> getLeaves();
}
