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
public interface BlockRedstoneWireBehavior extends BlockBehavior {
  BlockType<BlockRedstoneWireBehavior> REDSTONE_WIRE_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneWireBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_WIRE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .build();
}
