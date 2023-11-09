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
public interface BlockJigsawBehavior extends BlockBehavior {
  BlockType<BlockJigsawBehavior> JIGSAW_TYPE = BlockTypeBuilder
          .builder(BlockJigsawBehavior.class)
          .vanillaBlock(VanillaBlockId.JIGSAW)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ROTATION)
          .build();
}
