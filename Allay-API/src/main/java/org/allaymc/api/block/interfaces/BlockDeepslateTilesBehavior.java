package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateTilesBehavior extends BlockBehavior {
  BlockType<BlockDeepslateTilesBehavior> DEEPSLATE_TILES_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTilesBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILES)
          .build();
}
