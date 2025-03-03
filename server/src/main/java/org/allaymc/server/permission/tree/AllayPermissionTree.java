package org.allaymc.server.permission.tree;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.allaymc.api.permission.tree.PermissionNode;
import org.allaymc.api.permission.tree.PermissionTree;
import org.allaymc.api.utils.AllayStringUtils;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.function.Consumer;

import static org.allaymc.api.permission.DefaultPermissions.OPERATOR;
import static org.allaymc.api.permission.tree.PermissionTree.PermissionChangeType.ADD;
import static org.allaymc.api.permission.tree.PermissionTree.PermissionChangeType.REMOVE;

/**
 * @author daoge_cmd
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AllayPermissionTree implements PermissionTree {
    protected final String name;
    @Getter
    protected PermissionTree parent;
    protected PermissionNode root = new AllayRootPermissionNode("ROOT");
    protected Map<String, Consumer<PermissionChangeType>> listeners = new HashMap<>();

    public static PermissionTree create(String name) {
        return new AllayPermissionTree(name);
    }

    public static PermissionTree create() {
        return create("");
    }

    @Override
    public PermissionTree registerPermissionListener(String permission, Consumer<PermissionChangeType> callback) {
        listeners.put(permission, callback);
        return this;
    }

    @Override
    public @UnmodifiableView Map<String, Consumer<PermissionChangeType>> getPermissionListeners(boolean includeParent) {
        var map = new HashMap<>(listeners);
        if (parent != null && includeParent) {
            map.putAll(parent.getPermissionListeners(true));
        }
        return Collections.unmodifiableMap(map);
    }

    @Override
    public void notifyAllPermissionListeners() {
        var leaves = getLeaves();
        // When there is a parent, the parent's permissions are also considered
        // But the parent's listeners are not invoked
        if (parent != null) leaves.addAll(parent.getLeaves());
        leaves.forEach(leaf -> callListener(leaf.getFullName(), ADD));
    }

    @Override
    public boolean hasPermission(String permission) {
        if (parent != null && parent.hasPermission(permission)) return true;
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(permission, "."));
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
    public PermissionTree addPermission(String permission, boolean callListener) {
        if (parent != null && parent.hasPermission(permission)) return this;
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(permission, "."));
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
                if (callListener) callListener(permission, ADD);
            }
        }

        return this;
    }

    @Override
    public PermissionTree removePermission(String permission, boolean callListener) {
        var spilt = new LinkedList<>(AllayStringUtils.fastSplit(permission, "."));
        var node = root;
        while (!spilt.isEmpty()) {
            var nodeName = spilt.pop();
            var hasMatch = false;
            if (node.getLeaves().isEmpty()) return this;

            for (var leaf : node.getLeaves()) {
                if (!leaf.canMatch(nodeName)) continue;
                if (spilt.isEmpty()) {
                    if (callListener) callListener(permission, REMOVE);
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
    public PermissionTree copyFrom(PermissionTree parent) {
        this.listeners.putAll(parent.getPermissionListeners(true));
        parent.getLeaves(true).stream()
                .map(PermissionNode::getFullName)
                .forEach(this::addPermission);
        return this;
    }

    @Override
    public PermissionTree extendFrom(PermissionTree parent, boolean callListener) {
        this.parent = parent;
        if (callListener)
            parent.getLeaves(true).stream()
                    .map(PermissionNode::getFullName)
                    .forEach(permission -> callListener(permission, ADD));
        return this;
    }

    @Override
    public List<PermissionNode> getLeaves(boolean includeParent) {
        var leaves = new ArrayList<PermissionNode>();
        if (!root.getLeaves().isEmpty()) {
            findLeaf(root, leaves);
        }
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
    public PermissionTree setOp(boolean op) {
        if (op) {
            if (!isOp()) copyFrom(OPERATOR);
        } else {
            OPERATOR.getLeaves().stream().map(PermissionNode::getFullName).forEach(this::removePermission);
        }
        return this;
    }

    @Override
    public void clear() {
        getLeaves().stream().map(PermissionNode::getFullName).forEach(this::removePermission);
    }

    @Override
    public boolean containsSubSet(PermissionTree other) {
        return other.getLeaves(true).stream().map(PermissionNode::getFullName).allMatch(this::hasPermission);
    }

    protected void findLeaf(PermissionNode node, List<PermissionNode> dest) {
        if (node.isLeaf()) dest.add(node);
        else node.getLeaves().forEach(leaf -> findLeaf(leaf, dest));
    }

    protected void callListener(String permission, PermissionChangeType type) {
        var listener = listeners.get(permission);
        if (listener != null) listener.accept(type);
    }
}
