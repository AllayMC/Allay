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
public interface BlockCaveVinesBehavior extends BlockBehavior {
    BlockType<BlockCaveVinesBehavior> CAVE_VINES_TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesBehavior.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES)
            .setProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
}
