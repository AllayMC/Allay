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
public interface BlockRedFlowerBehavior extends BlockBehavior {
  BlockType<BlockRedFlowerBehavior> RED_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockRedFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_FLOWER)
          .setProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
          .build();
}
