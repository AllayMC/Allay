package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHopperBehavior extends BlockBehavior {
  BlockType<BlockHopperBehavior> HOPPER_TYPE = BlockTypeBuilder
          .builder(BlockHopperBehavior.class)
          .vanillaBlock(VanillaBlockId.HOPPER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
          .build();
}
