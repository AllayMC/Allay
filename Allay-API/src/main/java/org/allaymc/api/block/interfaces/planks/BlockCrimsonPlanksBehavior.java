package org.allaymc.api.block.interfaces.planks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonPlanksBehavior extends BlockBehavior {
  BlockType<BlockCrimsonPlanksBehavior> CRIMSON_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_PLANKS)
          .build();
}
