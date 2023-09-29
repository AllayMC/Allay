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
public interface BlockLitSmokerBehavior extends BlockBehavior {
  BlockType<BlockLitSmokerBehavior> LIT_SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockLitSmokerBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_SMOKER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
