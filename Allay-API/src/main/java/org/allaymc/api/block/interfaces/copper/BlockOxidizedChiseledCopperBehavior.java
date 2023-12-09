package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOxidizedChiseledCopperBehavior extends BlockBehavior {
  BlockType<BlockOxidizedChiseledCopperBehavior> OXIDIZED_CHISELED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedChiseledCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CHISELED_COPPER)
          .build();
}
