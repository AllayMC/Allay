package cn.allay.api.block.interfaces.bamboopressureplate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBambooPressurePlateBehavior extends BlockBehavior {
    BlockType<BlockBambooPressurePlateBehavior> BAMBOO_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockBambooPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
}
