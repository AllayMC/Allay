package cn.allay.api.block.interfaces.terracotta;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenTerracottaBehavior extends BlockBehavior {
  BlockType<BlockGreenTerracottaBehavior> GREEN_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockGreenTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_TERRACOTTA)
          .build();
}
