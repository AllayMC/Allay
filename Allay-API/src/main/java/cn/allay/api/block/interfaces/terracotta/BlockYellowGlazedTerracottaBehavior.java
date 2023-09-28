package cn.allay.api.block.interfaces.terracotta;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockYellowGlazedTerracottaBehavior extends BlockBehavior {
  BlockType<BlockYellowGlazedTerracottaBehavior> YELLOW_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockYellowGlazedTerracottaBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_GLAZED_TERRACOTTA)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
