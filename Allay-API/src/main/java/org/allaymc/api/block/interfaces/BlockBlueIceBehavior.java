package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueIceBehavior extends BlockBehavior {
  BlockType<BlockBlueIceBehavior> BLUE_ICE_TYPE = BlockTypeBuilder
          .builder(BlockBlueIceBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_ICE)
          .build();
}
