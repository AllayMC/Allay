package org.allaymc.api.block.interfaces.planks;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockAcaciaPlanksBehavior extends BlockBehavior {
  BlockType<BlockAcaciaPlanksBehavior> ACACIA_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaPlanksBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_PLANKS)
          .build();
}
