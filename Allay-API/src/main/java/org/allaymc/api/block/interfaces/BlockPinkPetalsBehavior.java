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
public interface BlockPinkPetalsBehavior extends BlockBehavior {
  BlockType<BlockPinkPetalsBehavior> PINK_PETALS_TYPE = BlockTypeBuilder
          .builder(BlockPinkPetalsBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_PETALS)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .build();
}
