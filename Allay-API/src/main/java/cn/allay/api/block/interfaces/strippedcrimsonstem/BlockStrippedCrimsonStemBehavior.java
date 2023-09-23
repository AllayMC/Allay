package cn.allay.api.block.interfaces.strippedcrimsonstem;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedCrimsonStemBehavior extends BlockBehavior {
    BlockType<BlockStrippedCrimsonStemBehavior> STRIPPED_CRIMSON_STEM_TYPE = BlockTypeBuilder
            .builder(BlockStrippedCrimsonStemBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_STEM)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
