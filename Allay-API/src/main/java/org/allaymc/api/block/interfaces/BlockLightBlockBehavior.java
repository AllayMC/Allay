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
public interface BlockLightBlockBehavior extends BlockBehavior {
  BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockLightBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.BLOCK_LIGHT_LEVEL)
          .build();
}
