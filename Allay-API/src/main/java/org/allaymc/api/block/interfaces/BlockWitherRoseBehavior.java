package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWitherRoseBehavior extends BlockBehavior {
  BlockType<BlockWitherRoseBehavior> WITHER_ROSE_TYPE = BlockTypeBuilder
          .builder(BlockWitherRoseBehavior.class)
          .vanillaBlock(VanillaBlockId.WITHER_ROSE)
          .build();
}
