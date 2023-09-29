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
public interface BlockChorusFlowerBehavior extends BlockBehavior {
  BlockType<BlockChorusFlowerBehavior> CHORUS_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockChorusFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.CHORUS_FLOWER)
          .setProperties(VanillaBlockPropertyTypes.AGE_6)
          .build();
}
