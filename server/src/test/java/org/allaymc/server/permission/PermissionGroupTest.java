package org.allaymc.server.permission;

import org.allaymc.api.permission.Permission;
import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class PermissionGroupTest {

    static Permission a = Permission.create("test.a");
    static Permission b = Permission.create("test.b");
    static Permission c = Permission.create("test.c");
    static Permission cmdTell = Permission.create("test.cmd.tell");
    static Permission cmdSay = Permission.create("test.cmd.say");
    static Permission cmdHello = Permission.create("test.cmd.hello");
    static Permission cmdAbc = Permission.create("test.cmd.abc");

    @Test
    void testAddAndRemove() {
        var group = PermissionGroup.create("test", Set.of(), Set.of(), false);

        group.addPermission(cmdTell);
        group.addPermission(cmdSay);
        group.addPermission(cmdHello);
        assertTrue(group.hasPermission(cmdTell));
        assertTrue(group.hasPermission(cmdSay));
        assertTrue(group.hasPermission(cmdHello));

        group.removePermission(cmdTell);
        assertFalse(group.hasPermission(cmdTell));
    }

    @Test
    void testExtend() {
        var parent = PermissionGroup
                .create("test parent", Set.of(), Set.of(), false)
                .addPermission(a)
                .addPermission(cmdAbc);
        var group = PermissionGroup
                .create("test", Set.of(), Set.of(), false)
                .addParent(parent)
                .addPermission(b);

        assertTrue(group.hasPermission(a));
        assertTrue(group.hasPermission(cmdAbc));
        assertTrue(group.hasPermission(b));

        parent.addPermission(c);
        assertTrue(group.hasPermission(c));

        parent.removePermission(a);
        assertFalse(group.hasPermission(a));
    }

    @Test
    void testListener() {
        AtomicBoolean f1 = new AtomicBoolean(false);
        Permission a1 = Permission.create("test.a1", "", (p, b) -> f1.set(b));
        PermissionGroup.create("test", Set.of(), Set.of(), false)
                .addPermission(a1);
        assertTrue(f1.get());
    }

    @Test
    void testHasPermissions() {
        var full = PermissionGroup
                .create("test", Set.of(), Set.of(), false)
                .addPermission(a)
                .addPermission(cmdAbc);
        var sub1 = PermissionGroup
                .create("test", Set.of(), Set.of(), false)
                .addPermission(a);
        var sub2 = PermissionGroup
                .create("test", Set.of(), Set.of(), false)
                .addPermission(cmdAbc);
        var invalidSub1 = PermissionGroup
                .create("test", Set.of(), Set.of(), false)
                .addPermission(b);
        var invalidSub2 = PermissionGroup
                .create("test", Set.of(), Set.of(), false)
                .addPermission(cmdHello);
        assertTrue(full.hasPermissions(sub1, false));
        assertTrue(full.hasPermissions(sub2, false));
        assertFalse(full.hasPermissions(invalidSub1, false));
        assertFalse(full.hasPermissions(invalidSub2, false));
    }

    @Test
    void testOp() {
        var group = PermissionGroup
                .create("test", Set.of(), Set.of(), false);
        assertFalse(group.isOperator());
        group.setOperator(true);
        assertTrue(group.isOperator());
        group.addPermission(cmdHello);
        assertTrue(group.isOperator());
        assertTrue(group.hasPermissions(PermissionGroups.OPERATOR, true));
    }

    @Test
    void testOverriding() {
        var parent = PermissionGroup
                .create("test parent", Set.of(), Set.of(), false)
                .addPermission(a);
        var group = PermissionGroup
                .create("test", Set.of(), Set.of(parent), false)
                // This will override the parent's permission
                .removePermission(a);
        assertTrue(parent.hasPermission(a));
        assertFalse(group.hasPermission(a));
        assertTrue(group.getPermissions().isEmpty());
    }
}
