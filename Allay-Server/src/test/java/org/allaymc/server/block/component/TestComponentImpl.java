package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.annotation.ComponentIdentifier;

/**
 * Allay Project 2023/4/16
 *
 * @author daoge_cmd
 */
public class TestComponentImpl implements TestComponent, BlockComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_component");
}
