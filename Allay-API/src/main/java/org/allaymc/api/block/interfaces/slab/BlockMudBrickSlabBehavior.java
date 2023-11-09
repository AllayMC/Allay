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
public interface BlockMudBrickSlabBehavior extends BlockBehavior {
  BlockType<BlockMudBrickSlabBehavior> MUD_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_SLAB)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
