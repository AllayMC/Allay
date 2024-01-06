package org.allaymc.server.perm;

import org.allaymc.server.perm.tree.AllayPermTree;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public class PermTreeTest {

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

        // test.cmd 与 test.cmd.* 意义不同
        tree.addPerm("test.cmd");
        assertFalse(tree.hasPerm("test.cmd.abc"));
        tree.addPerm("test.cmd.*");
        assertTrue(tree.hasPerm("test.cmd.abc"));
        tree.addPerm("*");
        assertTrue(tree.hasPerm("a.b.c"));

        tree.addPerm("*");
        assertTrue(tree.hasPerm("a.b.c"));

        // 不允许在检查权限时使用通配符
        assertThrows(IllegalArgumentException.class, () -> tree.hasPerm("test.cmd.*"));
    }

    @Test
    void testPermTreeCopy() {
        var parent = AllayPermTree
                .create()
                .addPerm("test.a")
                .addPerm("test.cmd.*");
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
                .addPerm("test.cmd.*");
        var tree = AllayPermTree
                .create()
                .extendFrom(parent)
                .addPerm("test.b");

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
        AtomicBoolean testFlag1 = new AtomicBoolean(false);
        var tree = AllayPermTree
                .create()
                .registerPermListener("test1.a", type -> testFlag1.set(true))
                .addPerm("test1.a");
        assertTrue(testFlag1.get());
        AtomicBoolean testFlag2 = new AtomicBoolean(false);
        tree.registerPermListener("test2.*", type -> testFlag2.set(true))
                .addPerm("test2.114514");
        assertTrue(testFlag2.get());
    }

    @Test
    void testOp() {
        var tree = AllayPermTree
                .create();
        assertFalse(tree.isOp());
        tree.setOp(true);
        assertTrue(tree.isOp());
        assertEquals(1, tree.getRoot().getLeaves().size());
        assertEquals("*", tree.getRoot().getLeaves().get(0).getFullName());
    }
}
