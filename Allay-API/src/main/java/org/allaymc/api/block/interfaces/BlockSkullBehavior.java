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
public interface BlockSkullBehavior extends BlockBehavior {
  BlockType<BlockSkullBehavior> SKULL_TYPE = BlockTypeBuilder
          .builder(BlockSkullBehavior.class)
          .vanillaBlock(VanillaBlockId.SKULL)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
