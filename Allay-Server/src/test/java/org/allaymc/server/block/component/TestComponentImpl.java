package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.server.component.annotation.Identifier;

/**
 * Allay Project 2023/4/16
 *
 * @author daoge_cmd
 */
public class TestComponentImpl implements TestComponent, BlockComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:test_component");
}
