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
public interface BlockUnpoweredRepeaterBehavior extends BlockBehavior {
  BlockType<BlockUnpoweredRepeaterBehavior> UNPOWERED_REPEATER_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredRepeaterBehavior.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.REPEATER_DELAY)
          .build();
}
