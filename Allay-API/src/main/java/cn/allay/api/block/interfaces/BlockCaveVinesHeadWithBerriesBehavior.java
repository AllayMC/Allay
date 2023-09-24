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
public interface BlockCaveVinesHeadWithBerriesBehavior extends BlockBehavior {
    BlockType<BlockCaveVinesHeadWithBerriesBehavior> CAVE_VINES_HEAD_WITH_BERRIES_TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesHeadWithBerriesBehavior.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES)
            .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
}
