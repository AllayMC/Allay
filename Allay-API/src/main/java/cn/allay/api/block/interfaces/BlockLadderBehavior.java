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
public interface BlockLadderBehavior extends BlockBehavior {
  BlockType<BlockLadderBehavior> LADDER_TYPE = BlockTypeBuilder
          .builder(BlockLadderBehavior.class)
          .vanillaBlock(VanillaBlockId.LADDER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
