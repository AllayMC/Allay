package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockVerdantFroglightBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockVerdantFroglightBehaviorInitializer {
  static void init() {
    BlockTypes.VERDANT_FROGLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockVerdantFroglightBehavior.class)
            .vanillaBlock(VanillaBlockId.VERDANT_FROGLIGHT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
