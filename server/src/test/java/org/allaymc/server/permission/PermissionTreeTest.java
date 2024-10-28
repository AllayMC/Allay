package org.allaymc.server.permission;

import org.allaymc.api.permission.tree.PermissionNode;
import org.allaymc.server.permission.tree.AllayPermissionTree;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.allaymc.api.permission.tree.PermissionTree.PermissionChangeType.ADD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
public class PermissionTreeTest {
    @Test
    void testPermNodeFullName() {
        var tree = AllayPermissionTree
                .create()
                .addPermission("test.cmd.tell")
                .addPermission("test.cmd.say")
                .addPermission("test.cmd.hello");
        var fullNames = tree.getLeaves().stream().map(PermissionNode::getFullName).collect(Collectors.toSet());
        assertTrue(fullNames.contains("test.cmd.tell"));
        assertTrue(fullNames.contains("test.cmd.say"));
        assertTrue(fullNames.contains("test.cmd.hello"));
    }

    @Test
    void testPermTree() {
        var tree = AllayPermissionTree.create();

        tree.addPermission("test.cmd.tell");
        tree.addPermission("test.cmd.say");
        tree.addPermission("test.cmd.hello");
        assertTrue(tree.hasPermission("test.cmd.tell"));
        assertTrue(tree.hasPermission("test.cmd.say"));
        assertTrue(tree.hasPermission("test.cmd.hello"));

        tree.removePermission("test.cmd.tell");
        assertFalse(tree.hasPermission("test.cmd.tell"));

        tree.removePermission("test.cmd");
        assertFalse(tree.hasPermission("test.cmd.say"));
        assertFalse(tree.hasPermission("test.cmd.hello"));
    }

    @Test
    void testPermTreeCopy() {
        var parent = AllayPermissionTree
                .create()
                .addPermission("test.a")
                .addPermission("test.cmd.abc");
        var tree = AllayPermissionTree
                .create()
                .copyFrom(parent)
                .addPermission("test.b");

        assertTrue(tree.hasPermission("test.a"));
        assertTrue(tree.hasPermission("test.cmd.abc"));
        assertTrue(tree.hasPermission("test.b"));

        parent.addPermission("test.d");
        assertFalse(tree.hasPermission("test.d"));

        parent.removePermission("test.a");
        assertTrue(tree.hasPermission("test.a"));
    }

    @Test
    void testPermTreeExtend() {
        var parent = AllayPermissionTree
                .create()
                .addPermission("test.a")
                .addPermission("test.cmd.abc");
        var f1 = new AtomicBoolean(false);
        var tree = AllayPermissionTree
                .create()
                .addPermission("test.b")
                .registerPermissionListener("test.a", type -> f1.set(type == ADD))
                .extendFrom(parent);

        assertTrue(f1.get());

        assertTrue(tree.hasPermission("test.a"));
        assertTrue(tree.hasPermission("test.cmd.abc"));
        assertTrue(tree.hasPermission("test.b"));

        parent.addPermission("test.d");
        assertTrue(tree.hasPermission("test.d"));

        parent.removePermission("test.a");
        assertFalse(tree.hasPermission("test.a"));
    }

    @Test
    void testPermListener() {
        AtomicBoolean f1 = new AtomicBoolean(false);
        AtomicBoolean f2 = new AtomicBoolean(false);
        AllayPermissionTree.create()
                .registerPermissionListener("test1.a", type -> f1.set(true))
                .registerPermissionListener("test1", type -> f2.set(true))
                .addPermission("test1.a");
        assertTrue(f1.get());
        assertFalse(f2.get());
    }

    @Test
    void testSubSet() {
        var full = AllayPermissionTree
                .create()
                .addPermission("test.a")
                .addPermission("test.cmd.abc");
        var sub1 = AllayPermissionTree
                .create()
                .addPermission("test.a");
        var sub2 = AllayPermissionTree
                .create()
                .addPermission("test.cmd.abc");
        var invalidSub1 = AllayPermissionTree
                .create()
                .addPermission("test.b");
        var invalidSub2 = AllayPermissionTree
                .create()
                .addPermission("test.cmd.def");
        assertTrue(full.containsSubSet(sub1));
        assertTrue(full.containsSubSet(sub2));
        assertFalse(full.containsSubSet(invalidSub1));
        assertFalse(full.containsSubSet(invalidSub2));
    }

    @Test
    void testNotifyAllPermissionListeners() {
        var parent = AllayPermissionTree
                .create()
                .addPermission("test.a")
                .addPermission("test.cmd.abc");
        var tree = AllayPermissionTree
                .create()
                .extendFrom(parent)
                .addPermission("test.b");
        AtomicBoolean f1 = new AtomicBoolean(false);
        AtomicBoolean f2 = new AtomicBoolean(false);
        AtomicBoolean f3 = new AtomicBoolean(false);
        tree.registerPermissionListener("test.a", type -> f1.set(type == ADD));
        tree.registerPermissionListener("test.cmd.abc", type -> f2.set(type == ADD));
        tree.registerPermissionListener("test.b", type -> f3.set(type == ADD));
        tree.notifyAllPermissionListeners();
        assertTrue(f1.get());
        assertTrue(f2.get());
        assertTrue(f3.get());
    }
}
