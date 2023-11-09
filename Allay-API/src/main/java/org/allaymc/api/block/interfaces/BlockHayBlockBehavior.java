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
public interface BlockHayBlockBehavior extends BlockBehavior {
  BlockType<BlockHayBlockBehavior> HAY_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHayBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.HAY_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
