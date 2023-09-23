package cn.allay.api.block.interfaces.pitcherplant;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPitcherPlantBehavior extends BlockBehavior {
    BlockType<BlockPitcherPlantBehavior> PITCHER_PLANT_TYPE = BlockTypeBuilder
            .builder(BlockPitcherPlantBehavior.class)
            .vanillaBlock(VanillaBlockId.PITCHER_PLANT)
            .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
}
