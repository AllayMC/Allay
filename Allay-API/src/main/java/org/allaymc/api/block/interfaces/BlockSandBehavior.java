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
public interface BlockSandBehavior extends BlockBehavior {
  BlockType<BlockSandBehavior> SAND_TYPE = BlockTypeBuilder
          .builder(BlockSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SAND)
          .setProperties(VanillaBlockPropertyTypes.SAND_TYPE)
          .build();
}
