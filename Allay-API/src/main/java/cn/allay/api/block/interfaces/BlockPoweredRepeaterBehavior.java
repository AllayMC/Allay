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
public interface BlockPoweredRepeaterBehavior extends BlockBehavior {
  BlockType<BlockPoweredRepeaterBehavior> POWERED_REPEATER_TYPE = BlockTypeBuilder
          .builder(BlockPoweredRepeaterBehavior.class)
          .vanillaBlock(VanillaBlockId.POWERED_REPEATER)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
          .build();
}
