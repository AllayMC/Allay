package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSeaLanternBehavior extends BlockBehavior {
  BlockType<BlockSeaLanternBehavior> SEA_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSeaLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.SEA_LANTERN)
          .build();
}
