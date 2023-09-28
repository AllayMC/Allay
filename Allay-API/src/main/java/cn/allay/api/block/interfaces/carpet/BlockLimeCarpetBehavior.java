package cn.allay.api.block.interfaces.carpet;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLimeCarpetBehavior extends BlockBehavior {
  BlockType<BlockLimeCarpetBehavior> LIME_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLimeCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_CARPET)
          .build();
}
