package org.allaymc.api.block.interfaces.stainedglass;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockHardStainedGlassBehavior> HARD_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .build();
}
