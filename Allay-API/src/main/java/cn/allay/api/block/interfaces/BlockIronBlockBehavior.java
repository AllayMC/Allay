package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockIronBlockBehavior extends BlockBehavior {
  BlockType<BlockIronBlockBehavior> IRON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockIronBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.IRON_BLOCK)
          .build();
}
