package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFungusBehavior extends BlockBehavior {
  BlockType<BlockCrimsonFungusBehavior> CRIMSON_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFungusBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FUNGUS)
          .build();
}
