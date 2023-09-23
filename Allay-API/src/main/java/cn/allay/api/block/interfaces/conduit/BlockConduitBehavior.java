package cn.allay.api.block.interfaces.conduit;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockConduitBehavior extends BlockBehavior {
    BlockType<BlockConduitBehavior> CONDUIT_TYPE = BlockTypeBuilder
            .builder(BlockConduitBehavior.class)
            .vanillaBlock(VanillaBlockId.CONDUIT)
            .build();
}
