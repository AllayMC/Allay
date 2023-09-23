package cn.allay.api.block.interfaces.pitchercrop;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPitcherCropBehavior extends BlockBehavior {
    BlockType<BlockPitcherCropBehavior> PITCHER_CROP_TYPE = BlockTypeBuilder
            .builder(BlockPitcherCropBehavior.class)
            .vanillaBlock(VanillaBlockId.PITCHER_CROP)
            .setProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
            .build();
}
