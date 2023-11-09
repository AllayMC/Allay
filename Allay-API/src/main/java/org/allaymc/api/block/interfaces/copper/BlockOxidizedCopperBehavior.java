package org.allaymc.api.block.interfaces.copper;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCopperBehavior extends BlockBehavior {
  BlockType<BlockOxidizedCopperBehavior> OXIDIZED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER)
          .build();
}
