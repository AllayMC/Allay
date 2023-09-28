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
public interface BlockSpruceFenceGateBehavior extends BlockBehavior {
  BlockType<BlockSpruceFenceGateBehavior> SPRUCE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockSpruceFenceGateBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_FENCE_GATE)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
          .build();
}
