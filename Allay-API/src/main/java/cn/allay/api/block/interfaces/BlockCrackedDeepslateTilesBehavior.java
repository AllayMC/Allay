package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrackedDeepslateTilesBehavior extends BlockBehavior {
    BlockType<BlockCrackedDeepslateTilesBehavior> CRACKED_DEEPSLATE_TILES_TYPE = BlockTypeBuilder
            .builder(BlockCrackedDeepslateTilesBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_TILES)
            .build();
}
