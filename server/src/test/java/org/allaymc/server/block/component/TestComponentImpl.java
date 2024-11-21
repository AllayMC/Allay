package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class TestComponentImpl implements TestComponent, BlockComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_component");
}
