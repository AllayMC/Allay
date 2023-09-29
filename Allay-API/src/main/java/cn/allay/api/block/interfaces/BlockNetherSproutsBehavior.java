package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherSproutsBehavior extends BlockBehavior {
  BlockType<BlockNetherSproutsBehavior> NETHER_SPROUTS_TYPE = BlockTypeBuilder
          .builder(BlockNetherSproutsBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_SPROUTS)
          .build();
}
