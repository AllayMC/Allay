package org.allaymc.api.block.interfaces.candlecake;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCandleCakeBehavior extends BlockBehavior {
    BlockType<BlockCandleCakeBehavior> CANDLE_CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCandleCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CANDLE_CAKE)
            .setProperties(VanillaBlockPropertyTypes.LIT)
            .build();
}