package org.allaymc.server.perm.tree;

import lombok.Getter;
import org.allaymc.api.perm.tree.PermNode;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.utils.AllayStringUtils;

import java.util.LinkedList;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
@Getter
public class AllayPermTree implements PermTree {

    protected PermNode root = new AllayRootPermNode("ROOT");

    protected AllayPermTree() {
    }

    public static PermTree create() {
        return new AllayPermTree();
    }

    @Override
    public boolean hasPerm(String perm) {
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(perm, "."));
        var node = root;
        while (!spilt.isEmpty()) {
            var nodeName = spilt.pop();
            var hasMatch = false;
            if (node.getLeaves().isEmpty()) {
                return false;
            }
            for (var leaf : node.getLeaves()) {
                if (leaf.isWildcardNode()) {
                    return true;
                } else if (leaf.canMatch(nodeName)) {
                    node = leaf;
                    hasMatch = true;
                    break;
                }
            }
            if (!hasMatch) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addPerm(String perm) {
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(perm, "."));
        var node = root;
        while (!spilt.isEmpty()) {
            var nodeName = spilt.pop();
            var hasMatch = false;
            if (!node.getLeaves().isEmpty()) {
                for (var leaf : node.getLeaves()) {
                    if (leaf.canMatch(nodeName)) {
                        node = leaf;
                        hasMatch = true;
                        break;
                    }
                }
            }
            if (!hasMatch) {
                node = node.addLeaf(nodeName);
            }
        }
    }

    @Override
    public void removePerm(String perm) {
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(perm, "."));
        var node = root;
        while (!spilt.isEmpty()) {
            var nodeName = spilt.pop();
            var hasMatch = false;
            if (!node.getLeaves().isEmpty()) {
                for (var leaf : node.getLeaves()) {
                    if (leaf.canMatch(nodeName)) {
                        if (spilt.isEmpty()) {
                            node.getLeaves().remove(leaf);
                            return;
                        } else {
                            node = leaf;
                            hasMatch = true;
                            break;
                        }
                    }
                }
            }
            if (!hasMatch) {
                return;
            }
        }
    }
}
