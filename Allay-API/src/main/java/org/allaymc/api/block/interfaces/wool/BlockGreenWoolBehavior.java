package org.allaymc.api.block.interfaces.wool;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenWoolBehavior extends BlockBehavior {
  BlockType<BlockGreenWoolBehavior> GREEN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockGreenWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_WOOL)
          .build();
}
