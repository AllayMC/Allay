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
public interface BlockCoralBlockBehavior extends BlockBehavior {
  BlockType<BlockCoralBlockBehavior> CORAL_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCoralBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.DEAD_BIT)
          .build();
}
