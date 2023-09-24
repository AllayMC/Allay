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
public interface BlockDaylightDetectorBehavior extends BlockBehavior {
    BlockType<BlockDaylightDetectorBehavior> DAYLIGHT_DETECTOR_TYPE = BlockTypeBuilder
            .builder(BlockDaylightDetectorBehavior.class)
            .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
}
