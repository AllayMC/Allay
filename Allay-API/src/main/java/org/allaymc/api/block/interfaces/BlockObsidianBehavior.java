package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockObsidianBehavior extends BlockBehavior {
  BlockType<BlockObsidianBehavior> OBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockObsidianBehavior.class)
          .vanillaBlock(VanillaBlockId.OBSIDIAN)
          .build();
}
