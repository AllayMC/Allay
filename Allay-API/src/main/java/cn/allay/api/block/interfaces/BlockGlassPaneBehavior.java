package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGlassPaneBehavior extends BlockBehavior {
  BlockType<BlockGlassPaneBehavior> GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.GLASS_PANE)
          .build();
}
