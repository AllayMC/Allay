package org.allaymc.server.perm;

import org.allaymc.api.perm.tree.PermNode;
import org.allaymc.server.perm.tree.AllayPermTree;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.allaymc.api.perm.tree.PermTree.PermChangeType.ADD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
public class PermTreeTest {
    @Test
    void testPermNodeFullName() {
        var tree = AllayPermTree
                .create()
                .addPerm("test.cmd.tell")
                .addPerm("test.cmd.say")
                .addPerm("test.cmd.hello");
        var fullNames = tree.getLeaves().stream().map(PermNode::getFullName).collect(Collectors.toSet());
        assertTrue(fullNames.contains("test.cmd.tell"));
        assertTrue(fullNames.contains("test.cmd.say"));
        assertTrue(fullNames.contains("test.cmd.hello"));
    }

    @Test
    void testPermTree() {
        var tree = AllayPermTree.create();

        tree.addPerm("test.cmd.tell");
        tree.addPerm("test.cmd.say");
        tree.addPerm("test.cmd.hello");
        assertTrue(tree.hasPerm("test.cmd.tell"));
        assertTrue(tree.hasPerm("test.cmd.say"));
        assertTrue(tree.hasPerm("test.cmd.hello"));

        tree.removePerm("test.cmd.tell");
        assertFalse(tree.hasPerm("test.cmd.tell"));

        tree.removePerm("test.cmd");
        assertFalse(tree.hasPerm("test.cmd.say"));
        assertFalse(tree.hasPerm("test.cmd.hello"));
    }

    @Test
    void testPermTreeCopy() {
        var parent = AllayPermTree
                .create()
                .addPerm("test.a")
                .addPerm("test.cmd.abc");
        var tree = AllayPermTree
                .create()
                .copyFrom(parent)
                .addPerm("test.b");

        assertTrue(tree.hasPerm("test.a"));
        assertTrue(tree.hasPerm("test.cmd.abc"));
        assertTrue(tree.hasPerm("test.b"));

        parent.addPerm("test.d");
        assertFalse(tree.hasPerm("test.d"));

        parent.removePerm("test.a");
        assertTrue(tree.hasPerm("test.a"));
    }

    @Test
    void testPermTreeExtend() {
        var parent = AllayPermTree
                .create()
                .addPerm("test.a")
                .addPerm("test.cmd.abc");
        var f1 = new AtomicBoolean(false);
        var tree = AllayPermTree
                .create()
                .addPerm("test.b")
                .registerPermListener("test.a", type -> f1.set(type == ADD))
                .extendFrom(parent);

        assertTrue(f1.get());

        assertTrue(tree.hasPerm("test.a"));
        assertTrue(tree.hasPerm("test.cmd.abc"));
        assertTrue(tree.hasPerm("test.b"));

        parent.addPerm("test.d");
        assertTrue(tree.hasPerm("test.d"));

        parent.removePerm("test.a");
        assertFalse(tree.hasPerm("test.a"));
    }

    @Test
    void testPermListener() {
        AtomicBoolean f1 = new AtomicBoolean(false);
        AtomicBoolean f2 = new AtomicBoolean(false);
        AllayPermTree.create()
                .registerPermListener("test1.a", type -> f1.set(true))
                .registerPermListener("test1", type -> f2.set(true))
                .addPerm("test1.a");
        assertTrue(f1.get());
        assertFalse(f2.get());
    }

    @Test
    void testSubSet() {
        var full = AllayPermTree
                .create()
                .addPerm("test.a")
                .addPerm("test.cmd.abc");
        var sub1 = AllayPermTree
                .create()
                .addPerm("test.a");
        var sub2 = AllayPermTree
                .create()
                .addPerm("test.cmd.abc");
        var invalidSub1 = AllayPermTree
                .create()
                .addPerm("test.b");
        var invalidSub2 = AllayPermTree
                .create()
                .addPerm("test.cmd.def");
        assertTrue(full.containsSubSet(sub1));
        assertTrue(full.containsSubSet(sub2));
        assertFalse(full.containsSubSet(invalidSub1));
        assertFalse(full.containsSubSet(invalidSub2));
    }

    @Test
    void testNotifyAllPermListeners() {
        var parent = AllayPermTree
                .create()
                .addPerm("test.a")
                .addPerm("test.cmd.abc");
        var tree = AllayPermTree
                .create()
                .extendFrom(parent)
                .addPerm("test.b");
        AtomicBoolean f1 = new AtomicBoolean(false);
        AtomicBoolean f2 = new AtomicBoolean(false);
        AtomicBoolean f3 = new AtomicBoolean(false);
        tree.registerPermListener("test.a", type -> f1.set(type == ADD));
        tree.registerPermListener("test.cmd.abc", type -> f2.set(type == ADD));
        tree.registerPermListener("test.b", type -> f3.set(type == ADD));
        tree.notifyAllPermListeners();
        assertTrue(f1.get());
        assertTrue(f2.get());
        assertTrue(f3.get());
    }
}
