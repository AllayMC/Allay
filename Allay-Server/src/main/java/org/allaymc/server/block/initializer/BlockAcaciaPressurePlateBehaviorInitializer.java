package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAcaciaPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.ACACIA_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
