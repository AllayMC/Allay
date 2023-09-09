package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackCandleStack extends ItemStack {
    ItemType<ItemBlackCandleStack> BLACK_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemBlackCandleStack.class)
            .vanillaItem(VanillaItemId.BLACK_CANDLE)
            .build();
}
