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
public interface BlockUnpoweredComparatorBehavior extends BlockBehavior {
  BlockType<BlockUnpoweredComparatorBehavior> UNPOWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredComparatorBehavior.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_COMPARATOR)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
          .build();
}
