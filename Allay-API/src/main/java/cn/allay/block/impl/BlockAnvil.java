package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAnvil extends Block {
    BlockType<BlockAnvil> TYPE = BlockTypeBuilder
            .builder(BlockAnvil.class)
            .vanillaBlock(VanillaBlockId.ANVIL)
            .property(VanillaBlockPropertyTypes.DAMAGE,
                    VanillaBlockPropertyTypes.DIRECTION)
            .build();
}
