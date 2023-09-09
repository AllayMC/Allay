package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackCandleCakeStack extends ItemStack {
    ItemType<ItemBlackCandleCakeStack> BLACK_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemBlackCandleCakeStack.class)
            .vanillaItem(VanillaItemId.BLACK_CANDLE_CAKE)
            .build();
}
