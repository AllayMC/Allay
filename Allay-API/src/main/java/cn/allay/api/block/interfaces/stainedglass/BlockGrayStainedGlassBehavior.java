package cn.allay.api.block.interfaces.stainedglass;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrayStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockGrayStainedGlassBehavior> GRAY_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockGrayStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_STAINED_GLASS)
          .build();
}
