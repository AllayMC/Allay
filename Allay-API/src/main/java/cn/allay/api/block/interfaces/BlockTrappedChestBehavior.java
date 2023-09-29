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
public interface BlockTrappedChestBehavior extends BlockBehavior {
  BlockType<BlockTrappedChestBehavior> TRAPPED_CHEST_TYPE = BlockTypeBuilder
          .builder(BlockTrappedChestBehavior.class)
          .vanillaBlock(VanillaBlockId.TRAPPED_CHEST)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
