package cn.allay.api.block.interfaces.stainedglasspane;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownStainedGlassPaneBehavior extends BlockBehavior {
  BlockType<BlockBrownStainedGlassPaneBehavior> BROWN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockBrownStainedGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_STAINED_GLASS_PANE)
          .build();
}
