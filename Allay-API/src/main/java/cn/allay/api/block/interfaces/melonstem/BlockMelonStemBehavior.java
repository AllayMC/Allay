package cn.allay.api.block.interfaces.melonstem;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMelonStemBehavior extends BlockBehavior {
    BlockType<BlockMelonStemBehavior> MELON_STEM_TYPE = BlockTypeBuilder
            .builder(BlockMelonStemBehavior.class)
            .vanillaBlock(VanillaBlockId.MELON_STEM)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
            .build();
}
