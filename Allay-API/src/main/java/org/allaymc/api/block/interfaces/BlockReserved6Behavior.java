package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockReserved6Behavior extends BlockBehavior {
  BlockType<BlockReserved6Behavior> RESERVED6_TYPE = BlockTypeBuilder
          .builder(BlockReserved6Behavior.class)
          .vanillaBlock(VanillaBlockId.RESERVED6)
          .build();
}
