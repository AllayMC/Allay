package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBarrierBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBarrierBehaviorInitializer {
  static void init() {
    BlockTypes.BARRIER_TYPE = BlockTypeBuilder
            .builder(BlockBarrierBehavior.class)
            .vanillaBlock(VanillaBlockId.BARRIER)
            .build();
  }
}
