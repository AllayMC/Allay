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
public interface BlockCrimsonStemBehavior extends BlockBehavior {
    BlockType<BlockCrimsonStemBehavior> CRIMSON_STEM_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonStemBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
