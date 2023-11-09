package org.allaymc.api.block.interfaces.wool;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLimeWoolBehavior extends BlockBehavior {
  BlockType<BlockLimeWoolBehavior> LIME_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLimeWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.LIME_WOOL)
          .build();
}
