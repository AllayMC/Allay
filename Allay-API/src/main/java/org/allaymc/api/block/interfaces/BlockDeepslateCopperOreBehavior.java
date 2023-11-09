package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateCopperOreBehavior extends BlockBehavior {
  BlockType<BlockDeepslateCopperOreBehavior> DEEPSLATE_COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateCopperOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_COPPER_ORE)
          .build();
}
