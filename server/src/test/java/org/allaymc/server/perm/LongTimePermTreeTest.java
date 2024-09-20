package org.allaymc.server.perm;

import org.allaymc.api.perm.DefaultPermissions;
import org.allaymc.server.perm.tree.AllayPermTree;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class LongTimePermTreeTest {
    @Test
    void testOp() {
        var tree = AllayPermTree
                .create();
        assertFalse(tree.isOp());
        tree.setOp(true);
        assertTrue(tree.isOp());
        tree.addPerm("test.extra");
        assertTrue(tree.isOp());
        assertTrue(tree.containsSubSet(DefaultPermissions.OPERATOR));
    }
}
