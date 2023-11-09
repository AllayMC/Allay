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
public interface BlockEndRodBehavior extends BlockBehavior {
  BlockType<BlockEndRodBehavior> END_ROD_TYPE = BlockTypeBuilder
          .builder(BlockEndRodBehavior.class)
          .vanillaBlock(VanillaBlockId.END_ROD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
