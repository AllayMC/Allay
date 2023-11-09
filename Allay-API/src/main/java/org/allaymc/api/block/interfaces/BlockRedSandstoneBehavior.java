package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedSandstoneBehavior extends BlockBehavior {
  BlockType<BlockRedSandstoneBehavior> RED_SANDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockRedSandstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SANDSTONE)
          .setProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
          .build();
}
