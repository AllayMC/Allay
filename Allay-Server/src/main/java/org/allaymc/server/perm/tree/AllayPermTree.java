package org.allaymc.server.perm.tree;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.perm.DefaultPermissions;
import org.allaymc.api.perm.tree.PermNode;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.utils.AllayStringUtils;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
@Getter
public class AllayPermTree implements PermTree {

    @Nullable
    @Getter
    protected PermTree parent;
    protected PermNode root = new AllayRootPermNode("ROOT");
    protected Map<String, Consumer<PermChangeType>> listeners = new HashMap<>();

    protected AllayPermTree() {
    }

    public static PermTree create() {
        return new AllayPermTree();
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
    public boolean hasPerm(String perm) {
        if (parent != null && parent.hasPerm(perm)) {
            return true;
        }
        if (perm.contains("*")) {
            throw new IllegalArgumentException("Using wildcard in method hasPerm() is not allowed!");
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
                    var listener = findListener(perm);
                    if (listener != null) listener.accept(PermChangeType.ADD);
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
                                var listener = findListener(perm);
                                if (listener != null) listener.accept(PermChangeType.REMOVE);
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
    public PermTree extendFrom(PermTree parent) {
        this.parent = parent;
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
        var rootLeaves = root.getLeaves();
        return rootLeaves.size() == 1 && rootLeaves.get(0).getName().equals("*");
    }

    @Override
    public PermTree setOp(boolean op) {
        if (op) {
            addPerm("*");
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

    protected void findLeaf(PermNode node, List<PermNode> dest) {
        if (node.isLeaf()) {
            dest.add(node);
        } else {
            for (var leaf : node.getLeaves()) {
                findLeaf(leaf, dest);
            }
        }
    }

    @Nullable
    protected Consumer<PermChangeType> findListener(String perm) {
        for (var entry : listeners.entrySet()) {
            var listenedPerm = entry.getKey();
            if (isPermSubset(listenedPerm, perm)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * @return 权限p2是否是权限p1的子集
     */
    protected boolean isPermSubset(String p1, String p2) {
        if (p1.equals(p2)) {
            return true;
        }
        if (!p1.endsWith("*")) {
            // p1没有通配符
            return false;
        }
        return p2.startsWith(p1.substring(0, p1.length() - 2));
    }
}
