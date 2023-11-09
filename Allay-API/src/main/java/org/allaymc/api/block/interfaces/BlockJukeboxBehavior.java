package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockJukeboxBehavior extends BlockBehavior {
  BlockType<BlockJukeboxBehavior> JUKEBOX_TYPE = BlockTypeBuilder
          .builder(BlockJukeboxBehavior.class)
          .vanillaBlock(VanillaBlockId.JUKEBOX)
          .build();
}
