package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPackedIceBehavior extends BlockBehavior {
  BlockType<BlockPackedIceBehavior> PACKED_ICE_TYPE = BlockTypeBuilder
          .builder(BlockPackedIceBehavior.class)
          .vanillaBlock(VanillaBlockId.PACKED_ICE)
          .build();
}
