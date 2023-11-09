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
public interface BlockDispenserBehavior extends BlockBehavior {
  BlockType<BlockDispenserBehavior> DISPENSER_TYPE = BlockTypeBuilder
          .builder(BlockDispenserBehavior.class)
          .vanillaBlock(VanillaBlockId.DISPENSER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .build();
}
