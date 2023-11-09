package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrackedDeepslateTilesBehavior extends BlockBehavior {
  BlockType<BlockCrackedDeepslateTilesBehavior> CRACKED_DEEPSLATE_TILES_TYPE = BlockTypeBuilder
          .builder(BlockCrackedDeepslateTilesBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_TILES)
          .build();
}
