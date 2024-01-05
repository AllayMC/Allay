package org.allaymc.server.command;

import org.allaymc.api.command.tree.node.RootNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Allay Project 2023/12/29
 *
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
