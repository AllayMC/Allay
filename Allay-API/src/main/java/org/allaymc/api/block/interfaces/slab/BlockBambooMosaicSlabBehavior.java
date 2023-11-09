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
public interface BlockBambooMosaicSlabBehavior extends BlockBehavior {
  BlockType<BlockBambooMosaicSlabBehavior> BAMBOO_MOSAIC_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_SLAB)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
