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
public interface BlockSculkVeinBehavior extends BlockBehavior {
  BlockType<BlockSculkVeinBehavior> SCULK_VEIN_TYPE = BlockTypeBuilder
          .builder(BlockSculkVeinBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_VEIN)
          .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .build();
}
