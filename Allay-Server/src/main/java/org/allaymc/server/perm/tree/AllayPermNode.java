package org.allaymc.server.perm.tree;

import lombok.Getter;
import org.allaymc.api.perm.tree.PermNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
@Getter
public class AllayPermNode implements PermNode {

    protected String name;
    protected List<PermNode> leaves = new ArrayList<>();
    protected PermNode parent;

    public AllayPermNode(String name, PermNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String getFullName() {
        return getFullName0(this, "");
    }

    protected String getFullName0(PermNode node, String str) {
        if (node.isRoot()) {
            return str;
        }
        if (!str.isEmpty()) {
            str = "." + str;
        }
        str = node.getName() + str;
        return getFullName0(node.getParent(), str);
    }

    @Override
    public boolean canMatch(String nodeName) {
        if (name.equals(WILDCARD)) {
            return true;
        }
        return name.equals(nodeName);
    }

    @Override
    public PermNode addLeaf(String nodeName) {
        if (nodeName.equals(WILDCARD)) {
            leaves.clear();
        }
        var node = new AllayPermNode(nodeName, this);
        leaves.add(node);
        return node;
    }

    @Override
    public boolean isLeaf() {
        return leaves.isEmpty();
    }
}
