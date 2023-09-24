package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTurtleEggBehavior extends BlockBehavior {
    BlockType<BlockTurtleEggBehavior> TURTLE_EGG_TYPE = BlockTypeBuilder
            .builder(BlockTurtleEggBehavior.class)
            .vanillaBlock(VanillaBlockId.TURTLE_EGG)
            .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE, VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
            .build();
}
