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
public interface BlockBeehiveBehavior extends BlockBehavior {
  BlockType<BlockBeehiveBehavior> BEEHIVE_TYPE = BlockTypeBuilder
          .builder(BlockBeehiveBehavior.class)
          .vanillaBlock(VanillaBlockId.BEEHIVE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .build();
}
