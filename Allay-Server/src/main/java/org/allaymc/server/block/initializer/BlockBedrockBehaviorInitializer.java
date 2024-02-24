package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBedrockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBedrockBehaviorInitializer {
  static void init() {
    BlockTypes.BEDROCK_TYPE = BlockTypeBuilder
            .builder(BlockBedrockBehavior.class)
            .vanillaBlock(VanillaBlockId.BEDROCK)
            .setProperties(VanillaBlockPropertyTypes.INFINIBURN_BIT)
            .build();
  }
}
