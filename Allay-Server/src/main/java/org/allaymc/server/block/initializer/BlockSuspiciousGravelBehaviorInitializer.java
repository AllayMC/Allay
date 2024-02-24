package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSuspiciousGravelBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSuspiciousGravelBehaviorInitializer {
  static void init() {
    BlockTypes.SUSPICIOUS_GRAVEL_TYPE = BlockTypeBuilder
            .builder(BlockSuspiciousGravelBehavior.class)
            .vanillaBlock(VanillaBlockId.SUSPICIOUS_GRAVEL)
            .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
