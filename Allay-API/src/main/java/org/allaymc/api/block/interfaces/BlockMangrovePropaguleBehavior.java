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
public interface BlockMangrovePropaguleBehavior extends BlockBehavior {
  BlockType<BlockMangrovePropaguleBehavior> MANGROVE_PROPAGULE_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePropaguleBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PROPAGULE)
          .setProperties(VanillaBlockPropertyTypes.HANGING, VanillaBlockPropertyTypes.PROPAGULE_STAGE)
          .build();
}
