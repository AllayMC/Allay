package cn.allay.api.block.interfaces.fencegate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakFenceGateBehavior extends BlockBehavior {
  BlockType<BlockDarkOakFenceGateBehavior> DARK_OAK_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .build();
}
