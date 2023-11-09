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
public interface BlockWarpedHyphaeBehavior extends BlockBehavior {
  BlockType<BlockWarpedHyphaeBehavior> WARPED_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.WARPED_HYPHAE)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
