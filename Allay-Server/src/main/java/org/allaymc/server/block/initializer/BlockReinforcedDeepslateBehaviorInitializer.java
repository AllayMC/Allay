package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockReinforcedDeepslateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockReinforcedDeepslateBehaviorInitializer {
  static void init() {
    BlockTypes.REINFORCED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockReinforcedDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.REINFORCED_DEEPSLATE)
            .build();
  }
}
