package cn.allay.api.block.interfaces.invisiblebedrock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockInvisibleBedrockBehavior extends BlockBehavior {
    BlockType<BlockInvisibleBedrockBehavior> INVISIBLE_BEDROCK_TYPE = BlockTypeBuilder
            .builder(BlockInvisibleBedrockBehavior.class)
            .vanillaBlock(VanillaBlockId.INVISIBLE_BEDROCK)
            .build();
}
