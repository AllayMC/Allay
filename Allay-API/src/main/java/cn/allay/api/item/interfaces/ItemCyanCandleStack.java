package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanCandleStack extends ItemStack {
    ItemType<ItemCyanCandleStack> CYAN_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemCyanCandleStack.class)
            .vanillaItem(VanillaItemId.CYAN_CANDLE)
            .build();
}
