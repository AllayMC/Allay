package cn.allay.api.block.interfaces.clientrequestplaceholderblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockClientRequestPlaceholderBlockBehavior extends BlockBehavior {
    BlockType<BlockClientRequestPlaceholderBlockBehavior> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockClientRequestPlaceholderBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
            .build();
}
