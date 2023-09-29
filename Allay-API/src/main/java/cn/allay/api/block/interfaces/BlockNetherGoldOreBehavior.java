package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherGoldOreBehavior extends BlockBehavior {
  BlockType<BlockNetherGoldOreBehavior> NETHER_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockNetherGoldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_GOLD_ORE)
          .build();
}
