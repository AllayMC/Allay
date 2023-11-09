package org.allaymc.api.block.interfaces.wool;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockYellowWoolBehavior extends BlockBehavior {
  BlockType<BlockYellowWoolBehavior> YELLOW_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockYellowWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_WOOL)
          .build();
}
