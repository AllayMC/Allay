package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/4/16
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "test_boolean")
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "test_enum")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "test_int")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "invalid_property_for_test")
public class TestComponentImplV2 implements TestComponent, BlockComponent {
    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_component");
}
