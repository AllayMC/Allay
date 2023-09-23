package cn.allay.api.block.interfaces.polishedblackstone;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstoneBehavior extends BlockBehavior {
    BlockType<BlockPolishedBlackstoneBehavior> POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE)
            .build();
}
