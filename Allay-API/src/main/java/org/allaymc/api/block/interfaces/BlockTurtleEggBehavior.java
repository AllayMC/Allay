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
public interface BlockTurtleEggBehavior extends BlockBehavior {
  BlockType<BlockTurtleEggBehavior> TURTLE_EGG_TYPE = BlockTypeBuilder
          .builder(BlockTurtleEggBehavior.class)
          .vanillaBlock(VanillaBlockId.TURTLE_EGG)
          .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE, VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
          .build();
}
