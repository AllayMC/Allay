package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateBrickSlab extends Block {
    BlockType<BlockDeepslateBrickSlab> TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBrickSlab.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
