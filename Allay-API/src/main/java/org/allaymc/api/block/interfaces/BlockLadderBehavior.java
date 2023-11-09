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
public interface BlockLadderBehavior extends BlockBehavior {
  BlockType<BlockLadderBehavior> LADDER_TYPE = BlockTypeBuilder
          .builder(BlockLadderBehavior.class)
          .vanillaBlock(VanillaBlockId.LADDER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
