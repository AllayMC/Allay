package org.allaymc.server.perm.tree;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.perm.DefaultPermissions;
import org.allaymc.api.perm.tree.PermNode;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.utils.AllayStringUtils;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;

import static org.allaymc.api.perm.tree.PermTree.PermChangeType.ADD;
import static org.allaymc.api.perm.tree.PermTree.PermChangeType.REMOVE;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
@Getter
public class AllayPermTree implements PermTree {
    @Getter
    protected PermTree parent;
    protected PermNode root = new AllayRootPermNode("ROOT");
    protected Map<String, Consumer<PermChangeType>> listeners = new HashMap<>();
    protected final String name;

    protected AllayPermTree(String name) {
        this.name = name;
    }

    public static PermTree create(String name) {
        return new AllayPermTree(name);
    }

    public static PermTree create() {
        return new AllayPermTree("");
    }

    @Override
    public PermTree registerPermListener(String perm, Consumer<PermChangeType> callback) {
        listeners.put(perm, callback);
        return this;
    }

    @Override
    public @UnmodifiableView Map<String, Consumer<PermChangeType>> getPermListeners() {
        return Collections.unmodifiableMap(listeners);
    }

    @Override
    public void notifyAllPermListeners() {
        var leaves = getLeaves();
        // 当存在parent时，parent的权限也会被考虑
        // 但是parent的监听器不会被调用
        if (parent != null) leaves.addAll(parent.getLeaves());
        for (var leaf : leaves) {
            String perm = leaf.getFullName();
            callListener(perm, ADD);
        }
    }

    @Override
    public boolean hasPerm(String perm) {
        if (parent != null && parent.hasPerm(perm)) {
            return true;
        }
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(perm, "."));
        var node = root;
        while (!spilt.isEmpty()) {
            var nodeName = spilt.pop();
            var hasMatch = false;
            if (node.getLeaves().isEmpty()) {
                return false;
            }
            for (var leaf : node.getLeaves()) {
                if (leaf.canMatch(nodeName)) {
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
    public PermTree addPerm(String perm, boolean callListener) {
        if (parent != null && parent.hasPerm(perm)) {
            return this;
        }
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
                if (callListener) {
                    callListener(perm, ADD);
                }
            }
        }
        return this;
    }

    @Override
    public PermTree removePerm(String perm, boolean callListener) {
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(perm, "."));
        var node = root;
        while (!spilt.isEmpty()) {
            var nodeName = spilt.pop();
            var hasMatch = false;
            if (!node.getLeaves().isEmpty()) {
                for (var leaf : node.getLeaves()) {
                    if (leaf.canMatch(nodeName)) {
                        if (spilt.isEmpty()) {
                            if (callListener) {
                                callListener(perm, REMOVE);
                            }
                            node.getLeaves().remove(leaf);
                            return this;
                        } else {
                            node = leaf;
                            hasMatch = true;
                            break;
                        }
                    }
                }
            }
            if (!hasMatch) {
                return this;
            }
        }
        return this;
    }

    @Override
    public PermTree copyFrom(PermTree parent) {
        for (var leaf : parent.getLeaves()) {
            addPerm(leaf.getFullName());
        }
        this.listeners.putAll(parent.getPermListeners());
        return this;
    }

    @Override
    public PermTree extendFrom(PermTree parent, boolean callListener) {
        this.parent = parent;
        if (callListener)
            parent.getLeaves().stream().map(PermNode::getFullName).forEach(perm -> callListener(perm, ADD));
        return this;
    }

    @Override
    public List<PermNode> getLeaves() {
        var leaves = new ArrayList<PermNode>();
        findLeaf(root, leaves);
        return leaves;
    }

    @Override
    public boolean isOp() {
        return containsSubSet(DefaultPermissions.OPERATOR);
    }

    @Override
    public PermTree setOp(boolean op) {
        if (op) {
            if (!isOp()) extendFrom(DefaultPermissions.OPERATOR);
        } else {
            clear();
            extendFrom(DefaultPermissions.MEMBER);
        }
        return this;
    }

    @Override
    public void clear() {
        for (var leaf : getLeaves()) {
            removePerm(leaf.getFullName());
        }
    }

    @Override
    public boolean containsSubSet(PermTree other) {
        return other.getLeaves().stream().map(PermNode::getFullName).allMatch(this::hasPerm);
    }

    protected void findLeaf(PermNode node, List<PermNode> dest) {
        if (node.isLeaf()) {
            dest.add(node);
        } else {
            for (var leaf : node.getLeaves()) {
                findLeaf(leaf, dest);
            }
        }
    }

    protected void callListener(String perm, PermChangeType type) {
        var listener = listeners.get(perm);
        if (listener != null) listener.accept(type);
    }
}
