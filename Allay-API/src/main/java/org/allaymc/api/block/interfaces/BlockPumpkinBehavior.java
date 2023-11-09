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
public interface BlockPumpkinBehavior extends BlockBehavior {
  BlockType<BlockPumpkinBehavior> PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockPumpkinBehavior.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .build();
}
