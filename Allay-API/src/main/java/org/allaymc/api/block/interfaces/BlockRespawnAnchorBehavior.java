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
public interface BlockRespawnAnchorBehavior extends BlockBehavior {
  BlockType<BlockRespawnAnchorBehavior> RESPAWN_ANCHOR_TYPE = BlockTypeBuilder
          .builder(BlockRespawnAnchorBehavior.class)
          .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR)
          .setProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
          .build();
}
