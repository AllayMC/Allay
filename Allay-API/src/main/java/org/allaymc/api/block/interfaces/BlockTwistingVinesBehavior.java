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
public interface BlockTwistingVinesBehavior extends BlockBehavior {
  BlockType<BlockTwistingVinesBehavior> TWISTING_VINES_TYPE = BlockTypeBuilder
          .builder(BlockTwistingVinesBehavior.class)
          .vanillaBlock(VanillaBlockId.TWISTING_VINES)
          .setProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
          .build();
}
