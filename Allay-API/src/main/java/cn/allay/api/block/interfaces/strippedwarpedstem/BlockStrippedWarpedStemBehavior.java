package cn.allay.api.block.interfaces.strippedwarpedstem;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedWarpedStemBehavior extends BlockBehavior {
    BlockType<BlockStrippedWarpedStemBehavior> STRIPPED_WARPED_STEM_TYPE = BlockTypeBuilder
            .builder(BlockStrippedWarpedStemBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
