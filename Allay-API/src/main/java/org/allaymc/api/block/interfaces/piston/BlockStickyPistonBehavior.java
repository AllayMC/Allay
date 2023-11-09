package org.allaymc.api.block.interfaces.piston;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

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
