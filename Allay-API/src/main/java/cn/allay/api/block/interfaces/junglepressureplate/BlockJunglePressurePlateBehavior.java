package cn.allay.api.block.interfaces.junglepressureplate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockJunglePressurePlateBehavior extends BlockBehavior {
    BlockType<BlockJunglePressurePlateBehavior> JUNGLE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockJunglePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
}
