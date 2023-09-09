package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCyanCandleCakeBehavior extends BlockBehavior {
    BlockType<BlockCyanCandleCakeBehavior> CYAN_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCyanCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
}
