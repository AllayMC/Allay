package org.allaymc.server.perm.tree;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.allaymc.api.perm.tree.PermNode;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.utils.AllayStringUtils;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;

import static org.allaymc.api.perm.DefaultPermissions.OPERATOR;
import static org.allaymc.api.perm.tree.PermTree.PermChangeType.ADD;
import static org.allaymc.api.perm.tree.PermTree.PermChangeType.REMOVE;

/**
 * @author daoge_cmd
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AllayPermTree implements PermTree {
    protected final String name;
    @Getter
    protected PermTree parent;
    protected PermNode root = new AllayRootPermNode("ROOT");
    protected Map<String, Consumer<PermChangeType>> listeners = new HashMap<>();

    public static PermTree create(String name) {
        return new AllayPermTree(name);
    }

    public static PermTree create() {
        return create("");
    }

    @Override
    public PermTree registerPermListener(String perm, Consumer<PermChangeType> callback) {
        listeners.put(perm, callback);
        return this;
    }

    @Override
    public @UnmodifiableView Map<String, Consumer<PermChangeType>> getPermListeners(boolean includeParent) {
        var map = new HashMap<>(listeners);
        if (parent != null && includeParent) {
            map.putAll(parent.getPermListeners(true));
        }
        return Collections.unmodifiableMap(map);
    }

    @Override
    public void notifyAllPermListeners() {
        var leaves = getLeaves();
        // When there is a parent, the parent's permissions are also considered
        // But the parent's listeners are not invoked
        if (parent != null) leaves.addAll(parent.getLeaves());
        leaves.forEach(leaf -> callListener(leaf.getFullName(), ADD));
    }

    @Override
    public boolean hasPerm(String perm) {
        if (parent != null && parent.hasPerm(perm)) return true;
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(perm, "."));
        var node = root;
        while (!spilt.isEmpty()) {
            var nodeName = spilt.pop();
            var hasMatch = false;
            if (node.getLeaves().isEmpty()) return false;

            for (var leaf : node.getLeaves()) {
                if (leaf.canMatch(nodeName)) {
                    node = leaf;
                    hasMatch = true;
                    break;
                }
            }

            if (!hasMatch) return false;
        }

        return true;
    }

    @Override
    public PermTree addPerm(String perm, boolean callListener) {
        if (parent != null && parent.hasPerm(perm)) return this;
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
                if (callListener) callListener(perm, ADD);
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
            if (node.getLeaves().isEmpty()) return this;

            for (var leaf : node.getLeaves()) {
                if (!leaf.canMatch(nodeName)) continue;
                if (spilt.isEmpty()) {
                    if (callListener) callListener(perm, REMOVE);
                    node.getLeaves().remove(leaf);
                    return this;
                } else {
                    node = leaf;
                    hasMatch = true;
                    break;
                }
            }

            if (!hasMatch) return this;
        }

        return this;
    }

    @Override
    public PermTree copyFrom(PermTree parent) {
        this.listeners.putAll(parent.getPermListeners(true));
        parent.getLeaves(true).stream()
                .map(PermNode::getFullName)
                .forEach(this::addPerm);
        return this;
    }

    @Override
    public PermTree extendFrom(PermTree parent, boolean callListener) {
        this.parent = parent;
        if (callListener)
            parent.getLeaves(true).stream()
                    .map(PermNode::getFullName)
                    .forEach(perm -> callListener(perm, ADD));
        return this;
    }

    @Override
    public List<PermNode> getLeaves(boolean includeParent) {
        var leaves = new ArrayList<PermNode>();
        if (!root.getLeaves().isEmpty())
            findLeaf(root, leaves);
        if (parent != null && includeParent) {
            leaves.addAll(parent.getLeaves(true));
        }
        return leaves;
    }

    @Override
    public boolean isOp() {
        return containsSubSet(OPERATOR);
    }

    @Override
    public PermTree setOp(boolean op) {
        if (op) {
            if (!isOp()) copyFrom(OPERATOR);
        } else {
            OPERATOR.getLeaves().stream().map(PermNode::getFullName).forEach(this::removePerm);
        }
        return this;
    }

    @Override
    public void clear() {
        getLeaves().stream().map(PermNode::getFullName).forEach(this::removePerm);
    }

    @Override
    public boolean containsSubSet(PermTree other) {
        return other.getLeaves(true).stream().map(PermNode::getFullName).allMatch(this::hasPerm);
    }

    protected void findLeaf(PermNode node, List<PermNode> dest) {
        if (node.isLeaf()) dest.add(node);
        else node.getLeaves().forEach(leaf -> findLeaf(leaf, dest));
    }

    protected void callListener(String perm, PermChangeType type) {
        var listener = listeners.get(perm);
        if (listener != null) listener.accept(type);
    }
}
