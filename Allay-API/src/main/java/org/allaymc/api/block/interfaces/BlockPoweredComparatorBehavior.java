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
public interface BlockPoweredComparatorBehavior extends BlockBehavior {
  BlockType<BlockPoweredComparatorBehavior> POWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockPoweredComparatorBehavior.class)
          .vanillaBlock(VanillaBlockId.POWERED_COMPARATOR)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
          .build();
}
