package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSuspiciousSandBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSuspiciousSandBehaviorInitializer {
  static void init() {
    BlockTypes.SUSPICIOUS_SAND_TYPE = BlockTypeBuilder
            .builder(BlockSuspiciousSandBehavior.class)
            .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND)
            .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
