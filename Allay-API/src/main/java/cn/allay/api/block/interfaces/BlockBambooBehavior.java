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
public interface BlockBambooBehavior extends BlockBehavior {
  BlockType<BlockBambooBehavior> BAMBOO_TYPE = BlockTypeBuilder
          .builder(BlockBambooBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO)
          .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.BAMBOO_LEAF_SIZE, VanillaBlockPropertyTypes.BAMBOO_STALK_THICKNESS)
          .build();
}
