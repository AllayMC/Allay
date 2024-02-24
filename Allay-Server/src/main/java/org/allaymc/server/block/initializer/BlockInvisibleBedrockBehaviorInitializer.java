package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockInvisibleBedrockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockInvisibleBedrockBehaviorInitializer {
  static void init() {
    BlockTypes.INVISIBLE_BEDROCK_TYPE = BlockTypeBuilder
            .builder(BlockInvisibleBedrockBehavior.class)
            .vanillaBlock(VanillaBlockId.INVISIBLE_BEDROCK)
            .build();
  }
}
