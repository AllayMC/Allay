package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChiseledCopperBehavior extends BlockBehavior {
  BlockType<BlockChiseledCopperBehavior> CHISELED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockChiseledCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_COPPER)
          .build();
}
