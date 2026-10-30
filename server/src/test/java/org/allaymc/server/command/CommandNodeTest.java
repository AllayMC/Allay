package org.allaymc.server.command;

import org.allaymc.server.command.tree.node.RootNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code CommandNodeTest} checks unsupported ancestry operations on the
 * structural command root.
 *
 * @author daoge_cmd
 */
public class CommandNodeTest {
    /**
     * Creates an isolated structural root without argument children. The
     * assertions protect its refusal to expose a parent or traverse upward
     * through the default single-level navigation helper.
     */
    @Test
    void testRootNode() {
        var node = new RootNode();
        assertThrows(UnsupportedOperationException.class, node::parent);
        assertThrows(UnsupportedOperationException.class, node::up);
    }
}
