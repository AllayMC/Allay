package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCandleStack extends ItemStack {
    ItemType<ItemCandleStack> CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemCandleStack.class)
            .vanillaItem(VanillaItemId.CANDLE)
            .build();
}
