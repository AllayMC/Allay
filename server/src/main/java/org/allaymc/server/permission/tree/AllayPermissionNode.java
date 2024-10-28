package org.allaymc.server.permission.tree;

import lombok.Getter;
import org.allaymc.api.permission.tree.PermissionNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
public class AllayPermissionNode implements PermissionNode {

    protected String name;
    protected List<PermissionNode> leaves = new ArrayList<>();
    protected PermissionNode parent;

    public AllayPermissionNode(String name, PermissionNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String getFullName() {
        return getFullName0(this, "");
    }

    protected String getFullName0(PermissionNode node, String str) {
        if (node.isRoot()) return str;
        if (!str.isEmpty()) str = "." + str;
        str = node.getName() + str;
        return getFullName0(node.getParent(), str);
    }

    @Override
    public boolean canMatch(String nodeName) {
        return name.equals(nodeName);
    }

    @Override
    public PermissionNode addLeaf(String nodeName) {
        var node = new AllayPermissionNode(nodeName, this);
        leaves.add(node);
        return node;
    }

    @Override
    public boolean isLeaf() {
        return leaves.isEmpty();
    }
}
