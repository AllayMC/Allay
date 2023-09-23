package cn.allay.api.block.interfaces.chiseledpolishedblackstone;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChiseledPolishedBlackstoneBehavior extends BlockBehavior {
    BlockType<BlockChiseledPolishedBlackstoneBehavior> CHISELED_POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
            .builder(BlockChiseledPolishedBlackstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_POLISHED_BLACKSTONE)
            .build();
}
