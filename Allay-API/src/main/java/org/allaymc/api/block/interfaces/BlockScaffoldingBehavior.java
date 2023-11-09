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
public interface BlockScaffoldingBehavior extends BlockBehavior {
  BlockType<BlockScaffoldingBehavior> SCAFFOLDING_TYPE = BlockTypeBuilder
          .builder(BlockScaffoldingBehavior.class)
          .vanillaBlock(VanillaBlockId.SCAFFOLDING)
          .setProperties(VanillaBlockPropertyTypes.STABILITY, VanillaBlockPropertyTypes.STABILITY_CHECK)
          .build();
}
