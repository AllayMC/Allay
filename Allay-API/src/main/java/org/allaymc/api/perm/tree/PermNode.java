package org.allaymc.api.perm.tree;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public interface PermNode {

    String getName();

    String getFullName();

    boolean canMatch(String nodeName);

    PermNode getParent();

    PermNode addLeaf(String nodeName);

    boolean isLeaf();

    default boolean isRoot() {
        return false;
    }

    List<PermNode> getLeaves();
}
