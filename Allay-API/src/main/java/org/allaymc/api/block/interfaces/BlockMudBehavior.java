package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMudBehavior extends BlockBehavior {
  BlockType<BlockMudBehavior> MUD_TYPE = BlockTypeBuilder
          .builder(BlockMudBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD)
          .build();
}
