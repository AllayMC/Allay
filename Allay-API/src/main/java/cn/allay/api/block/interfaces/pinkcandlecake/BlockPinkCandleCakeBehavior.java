package cn.allay.api.block.interfaces.pinkcandlecake;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkCandleCakeBehavior extends BlockBehavior {
    BlockType<BlockPinkCandleCakeBehavior> PINK_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockPinkCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
}
