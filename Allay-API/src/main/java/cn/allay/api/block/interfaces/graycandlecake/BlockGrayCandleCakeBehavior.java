package cn.allay.api.block.interfaces.graycandlecake;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrayCandleCakeBehavior extends BlockBehavior {
    BlockType<BlockGrayCandleCakeBehavior> GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockGrayCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
}
