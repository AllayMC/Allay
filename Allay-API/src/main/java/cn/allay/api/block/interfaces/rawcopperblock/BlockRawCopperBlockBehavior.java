package cn.allay.api.block.interfaces.rawcopperblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRawCopperBlockBehavior extends BlockBehavior {
  BlockType<BlockRawCopperBlockBehavior> RAW_COPPER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawCopperBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.RAW_COPPER_BLOCK)
          .build();
}
