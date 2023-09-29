package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
