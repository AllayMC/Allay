package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSculkShriekerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculkShriekerBehaviorInitializer {
  static void init() {
    BlockTypes.SCULK_SHRIEKER_TYPE = BlockTypeBuilder
            .builder(BlockSculkShriekerBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_SHRIEKER)
            .setProperties(VanillaBlockPropertyTypes.ACTIVE, VanillaBlockPropertyTypes.CAN_SUMMON)
            .build();
  }
}
