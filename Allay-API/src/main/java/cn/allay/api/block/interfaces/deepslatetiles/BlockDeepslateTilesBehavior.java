package cn.allay.api.block.interfaces.deepslatetiles;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateTilesBehavior extends BlockBehavior {
    BlockType<BlockDeepslateTilesBehavior> DEEPSLATE_TILES_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateTilesBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_TILES)
            .build();
}
