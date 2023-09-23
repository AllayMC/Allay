package cn.allay.api.block.interfaces.netherwartblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherWartBlockBehavior extends BlockBehavior {
    BlockType<BlockNetherWartBlockBehavior> NETHER_WART_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockNetherWartBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_WART_BLOCK)
            .build();
}
