package cn.allay.api.block.interfaces.stainedglass;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockBrownStainedGlassBehavior> BROWN_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockBrownStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_STAINED_GLASS)
          .build();
}
