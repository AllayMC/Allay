package cn.allay.api.block.interfaces.blackstone;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlackstoneBehavior extends BlockBehavior {
    BlockType<BlockBlackstoneBehavior> BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE)
            .build();
}
