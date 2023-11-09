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
public interface BlockStructureBlockBehavior extends BlockBehavior {
  BlockType<BlockStructureBlockBehavior> STRUCTURE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStructureBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
          .build();
}
