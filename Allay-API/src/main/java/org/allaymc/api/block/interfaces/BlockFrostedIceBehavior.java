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
public interface BlockFrostedIceBehavior extends BlockBehavior {
  BlockType<BlockFrostedIceBehavior> FROSTED_ICE_TYPE = BlockTypeBuilder
          .builder(BlockFrostedIceBehavior.class)
          .vanillaBlock(VanillaBlockId.FROSTED_ICE)
          .setProperties(VanillaBlockPropertyTypes.AGE_4)
          .build();
}
