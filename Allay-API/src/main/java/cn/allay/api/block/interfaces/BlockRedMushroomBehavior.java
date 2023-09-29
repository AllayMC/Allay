package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedMushroomBehavior extends BlockBehavior {
  BlockType<BlockRedMushroomBehavior> RED_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroomBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM)
          .build();
}
