package cn.allay.api.block.interfaces.brewingstand;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrewingStandBehavior extends BlockBehavior {
    BlockType<BlockBrewingStandBehavior> BREWING_STAND_TYPE = BlockTypeBuilder
            .builder(BlockBrewingStandBehavior.class)
            .vanillaBlock(VanillaBlockId.BREWING_STAND)
            .setProperties(VanillaBlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
            .build();
}
