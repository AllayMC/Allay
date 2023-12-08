package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCopperGrateBehavior extends BlockBehavior {
  BlockType<BlockOxidizedCopperGrateBehavior> OXIDIZED_COPPER_GRATE_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCopperGrateBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER_GRATE)
          .build();
}
