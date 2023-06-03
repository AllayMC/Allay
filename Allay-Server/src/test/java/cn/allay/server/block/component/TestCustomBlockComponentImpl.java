package cn.allay.server.block.component;

import cn.allay.api.block.component.impl.custom.CustomBlockComponentImpl;
import cn.allay.api.identifier.Identifier;

/**
 * Author: Cool_Loong <br>
 * Date: 6/3/2023 <br>
 * Allay Project
 */
public class TestCustomBlockComponentImpl extends CustomBlockComponentImpl {
    @Override
    public Identifier getIdentifier() {
        return new Identifier("minecraft:test_custom_block_component");
    }
}
