package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockOchreFroglightBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOchreFroglightBehaviorInitializer {
  static void init() {
    BlockTypes.OCHRE_FROGLIGHT_TYPE = BlockTypeBuilder
            .builder(BlockOchreFroglightBehavior.class)
            .vanillaBlock(VanillaBlockId.OCHRE_FROGLIGHT)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
