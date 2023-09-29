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
public interface BlockVineBehavior extends BlockBehavior {
  BlockType<BlockVineBehavior> VINE_TYPE = BlockTypeBuilder
          .builder(BlockVineBehavior.class)
          .vanillaBlock(VanillaBlockId.VINE)
          .setProperties(VanillaBlockPropertyTypes.VINE_DIRECTION_BITS)
          .build();
}
