package cn.allay.api.block.interfaces.terracotta;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayTerracottaBehavior extends BlockBehavior {
  BlockType<BlockLightGrayTerracottaBehavior> LIGHT_GRAY_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_TERRACOTTA)
          .build();
}
