package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBedrockBehavior extends BlockBehavior {
  BlockType<BlockBedrockBehavior> BEDROCK_TYPE = BlockTypeBuilder
          .builder(BlockBedrockBehavior.class)
          .vanillaBlock(VanillaBlockId.BEDROCK)
          .setProperties(VanillaBlockPropertyTypes.INFINIBURN_BIT)
          .build();
}
