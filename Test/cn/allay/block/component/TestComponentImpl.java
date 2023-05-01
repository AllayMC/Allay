package cn.allay.block.component;

import cn.allay.block.component.annotation.RequireBlockProperty;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/16 <br>
 * Allay Project <br>
 */
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "test_boolean")
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "test_enum")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "test_int")
public class TestComponentImpl implements TestComponent, BlockComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_component");

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
