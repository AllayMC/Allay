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
public interface BlockEndRodBehavior extends BlockBehavior {
  BlockType<BlockEndRodBehavior> END_ROD_TYPE = BlockTypeBuilder
          .builder(BlockEndRodBehavior.class)
          .vanillaBlock(VanillaBlockId.END_ROD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
