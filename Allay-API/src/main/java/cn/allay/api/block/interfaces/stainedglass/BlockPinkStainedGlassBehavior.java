package cn.allay.api.block.interfaces.stainedglass;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockPinkStainedGlassBehavior> PINK_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockPinkStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_STAINED_GLASS)
          .build();
}
