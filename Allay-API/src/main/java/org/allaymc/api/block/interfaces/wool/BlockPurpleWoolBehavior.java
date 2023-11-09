package org.allaymc.api.block.interfaces.wool;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpleWoolBehavior extends BlockBehavior {
  BlockType<BlockPurpleWoolBehavior> PURPLE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockPurpleWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPLE_WOOL)
          .build();
}
