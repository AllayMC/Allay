package cn.allay.api.block.interfaces.bluecandlecake;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueCandleCakeBehavior extends BlockBehavior {
    BlockType<BlockBlueCandleCakeBehavior> BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockBlueCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
}
