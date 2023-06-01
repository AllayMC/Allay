package cn.allay.api.block.component.impl.custom;

import cn.allay.api.block.component.BlockComponentImpl;
import cn.allay.api.identifier.Identifier;

/**
 * Author: Cool_Loong <br>
 * Date: 6/1/2023 <br>
 * Allay Project
 */
public class CustomBlockComponentImpl implements BlockComponentImpl, CustomBlockComponent {
    public static final Identifier IDENTIFIER = new Identifier("minecraft:custom_block_component");

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
