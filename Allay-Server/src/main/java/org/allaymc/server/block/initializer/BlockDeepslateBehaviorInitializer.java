package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
