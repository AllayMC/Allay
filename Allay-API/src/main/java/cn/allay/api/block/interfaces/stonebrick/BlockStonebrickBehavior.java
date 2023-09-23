package cn.allay.api.block.interfaces.stonebrick;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStonebrickBehavior extends BlockBehavior {
    BlockType<BlockStonebrickBehavior> STONEBRICK_TYPE = BlockTypeBuilder
            .builder(BlockStonebrickBehavior.class)
            .vanillaBlock(VanillaBlockId.STONEBRICK)
            .setProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
            .build();
}
