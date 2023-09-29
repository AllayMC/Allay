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
public interface BlockBeeNestBehavior extends BlockBehavior {
  BlockType<BlockBeeNestBehavior> BEE_NEST_TYPE = BlockTypeBuilder
          .builder(BlockBeeNestBehavior.class)
          .vanillaBlock(VanillaBlockId.BEE_NEST)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .build();
}
