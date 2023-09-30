package cn.allay.api.block.interfaces.stainedglasspane;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrayStainedGlassPaneBehavior extends BlockBehavior {
  BlockType<BlockGrayStainedGlassPaneBehavior> GRAY_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockGrayStainedGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_STAINED_GLASS_PANE)
          .build();
}
