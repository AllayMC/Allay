package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDiamondOreBehavior extends BlockBehavior {
  BlockType<BlockDiamondOreBehavior> DIAMOND_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDiamondOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DIAMOND_ORE)
          .build();
}
