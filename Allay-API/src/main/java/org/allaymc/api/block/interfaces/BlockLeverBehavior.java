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
public interface BlockLeverBehavior extends BlockBehavior {
  BlockType<BlockLeverBehavior> LEVER_TYPE = BlockTypeBuilder
          .builder(BlockLeverBehavior.class)
          .vanillaBlock(VanillaBlockId.LEVER)
          .setProperties(VanillaBlockPropertyTypes.LEVER_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
          .build();
}
