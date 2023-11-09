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
public interface BlockStrippedCrimsonHyphaeBehavior extends BlockBehavior {
  BlockType<BlockStrippedCrimsonHyphaeBehavior> STRIPPED_CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCrimsonHyphaeBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_HYPHAE)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
