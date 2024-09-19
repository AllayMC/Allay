package org.allaymc.server.command;

import org.allaymc.server.command.tree.node.RootNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author daoge_cmd
 */
public class CommandNodeTest {
    @Test
    void testRootNode() {
        var node = new RootNode();
        assertThrows(UnsupportedOperationException.class, node::parent);
        assertThrows(UnsupportedOperationException.class, node::up);
    }
}
