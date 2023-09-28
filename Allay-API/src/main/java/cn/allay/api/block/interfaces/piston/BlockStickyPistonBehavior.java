package cn.allay.api.block.interfaces.piston;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStickyPistonBehavior extends BlockBehavior {
  BlockType<BlockStickyPistonBehavior> STICKY_PISTON_TYPE = BlockTypeBuilder
          .builder(BlockStickyPistonBehavior.class)
          .vanillaBlock(VanillaBlockId.STICKY_PISTON)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
