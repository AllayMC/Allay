package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPearlescentFroglightBehavior extends BlockBehavior {
  BlockType<BlockPearlescentFroglightBehavior> PEARLESCENT_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockPearlescentFroglightBehavior.class)
          .vanillaBlock(VanillaBlockId.PEARLESCENT_FROGLIGHT)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
