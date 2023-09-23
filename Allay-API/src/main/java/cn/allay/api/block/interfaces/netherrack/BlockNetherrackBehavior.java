package cn.allay.api.block.interfaces.netherrack;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherrackBehavior extends BlockBehavior {
    BlockType<BlockNetherrackBehavior> NETHERRACK_TYPE = BlockTypeBuilder
            .builder(BlockNetherrackBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHERRACK)
            .build();
}
