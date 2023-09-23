package cn.allay.api.block.interfaces.warpedstem;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedStemBehavior extends BlockBehavior {
    BlockType<BlockWarpedStemBehavior> WARPED_STEM_TYPE = BlockTypeBuilder
            .builder(BlockWarpedStemBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
