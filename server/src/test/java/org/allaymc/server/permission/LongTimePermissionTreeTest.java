package org.allaymc.server.permission;

import org.allaymc.api.permission.DefaultPermissions;
import org.allaymc.server.permission.tree.AllayPermissionTree;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class LongTimePermissionTreeTest {
    @Test
    void testOp() {
        var tree = AllayPermissionTree
                .create();
        assertFalse(tree.isOp());
        tree.setOp(true);
        assertTrue(tree.isOp());
        tree.addPermission("test.extra");
        assertTrue(tree.isOp());
        assertTrue(tree.containsSubSet(DefaultPermissions.OPERATOR));
    }
}
