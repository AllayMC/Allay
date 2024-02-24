package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockInfestedDeepslateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockInfestedDeepslateBehaviorInitializer {
  static void init() {
    BlockTypes.INFESTED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockInfestedDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.INFESTED_DEEPSLATE)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
