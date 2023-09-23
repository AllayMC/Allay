package cn.allay.api.block.interfaces.birchtrapdoor;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBirchTrapdoorBehavior extends BlockBehavior {
    BlockType<BlockBirchTrapdoorBehavior> BIRCH_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockBirchTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
}
