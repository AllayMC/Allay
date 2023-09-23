package cn.allay.api.item.interfaces.whitecandle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteCandleStack extends ItemStack {
    ItemType<ItemWhiteCandleStack> WHITE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteCandleStack.class)
            .vanillaItem(VanillaItemId.WHITE_CANDLE)
            .build();
}
