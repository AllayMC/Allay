package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTintedGlassBehavior extends BlockBehavior {
  BlockType<BlockTintedGlassBehavior> TINTED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockTintedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.TINTED_GLASS)
          .build();
}
