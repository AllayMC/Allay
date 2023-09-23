package cn.allay.api.block.interfaces.stone;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStoneBehavior extends BlockBehavior {
    BlockType<BlockStoneBehavior> STONE_TYPE = BlockTypeBuilder
            .builder(BlockStoneBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE)
            .setProperties(VanillaBlockPropertyTypes.STONE_TYPE)
            .build();
}
