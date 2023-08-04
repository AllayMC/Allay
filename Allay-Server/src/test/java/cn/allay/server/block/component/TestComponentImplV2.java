package cn.allay.server.block.component;

import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.annotation.RequireBlockProperty;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.identifier.Identifier;

/**
 * Allay Project 2023/4/16
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "test_boolean")
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "test_enum")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "test_int")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "invalid_property_for_test")
public class TestComponentImplV2 implements TestComponent, BlockComponentImpl {
    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_component");
}
