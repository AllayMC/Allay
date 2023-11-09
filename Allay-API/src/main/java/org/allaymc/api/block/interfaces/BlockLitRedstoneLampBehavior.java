package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLitRedstoneLampBehavior extends BlockBehavior {
  BlockType<BlockLitRedstoneLampBehavior> LIT_REDSTONE_LAMP_TYPE = BlockTypeBuilder
          .builder(BlockLitRedstoneLampBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_REDSTONE_LAMP)
          .build();
}
