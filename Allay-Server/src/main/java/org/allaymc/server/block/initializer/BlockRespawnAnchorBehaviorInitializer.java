package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRespawnAnchorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRespawnAnchorBehaviorInitializer {
  static void init() {
    BlockTypes.RESPAWN_ANCHOR_TYPE = BlockTypeBuilder
            .builder(BlockRespawnAnchorBehavior.class)
            .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR)
            .setProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
            .build();
  }
}
