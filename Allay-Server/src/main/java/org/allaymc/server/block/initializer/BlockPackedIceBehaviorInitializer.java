package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPackedIceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPackedIceBehaviorInitializer {
  static void init() {
    BlockTypes.PACKED_ICE_TYPE = BlockTypeBuilder
            .builder(BlockPackedIceBehavior.class)
            .vanillaBlock(VanillaBlockId.PACKED_ICE)
            .build();
  }
}
