package cn.allay.api.block.interfaces.terracotta;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownTerracottaBehavior extends BlockBehavior {
  BlockType<BlockBrownTerracottaBehavior> BROWN_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBrownTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_TERRACOTTA)
          .build();
}
