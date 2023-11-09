package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherreactorBehavior extends BlockBehavior {
  BlockType<BlockNetherreactorBehavior> NETHERREACTOR_TYPE = BlockTypeBuilder
          .builder(BlockNetherreactorBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHERREACTOR)
          .build();
}
