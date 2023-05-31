package cn.allay.server.block.component;

import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.block.component.annotation.RequireBlockProperty;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.identifier.Identifier;

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
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
