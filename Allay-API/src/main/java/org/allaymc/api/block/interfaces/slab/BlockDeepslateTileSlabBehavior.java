package org.allaymc.api.block.interfaces.slab;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateTileSlabBehavior extends BlockBehavior {
  BlockType<BlockDeepslateTileSlabBehavior> DEEPSLATE_TILE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
