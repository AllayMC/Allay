package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenCandleStack extends ItemStack {
    ItemType<ItemGreenCandleStack> GREEN_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemGreenCandleStack.class)
            .vanillaItem(VanillaItemId.GREEN_CANDLE)
            .build();
}
