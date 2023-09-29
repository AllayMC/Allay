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
public interface BlockPumpkinStemBehavior extends BlockBehavior {
  BlockType<BlockPumpkinStemBehavior> PUMPKIN_STEM_TYPE = BlockTypeBuilder
          .builder(BlockPumpkinStemBehavior.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN_STEM)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .build();
}
