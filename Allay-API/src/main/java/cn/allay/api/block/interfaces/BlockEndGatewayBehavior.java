package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockEndGatewayBehavior extends BlockBehavior {
    BlockType<BlockEndGatewayBehavior> END_GATEWAY_TYPE = BlockTypeBuilder
            .builder(BlockEndGatewayBehavior.class)
            .vanillaBlock(VanillaBlockId.END_GATEWAY)
            .build();
}
