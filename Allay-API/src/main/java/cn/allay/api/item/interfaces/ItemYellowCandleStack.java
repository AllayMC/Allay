package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowCandleStack extends ItemStack {
    ItemType<ItemYellowCandleStack> YELLOW_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemYellowCandleStack.class)
            .vanillaItem(VanillaItemId.YELLOW_CANDLE)
            .build();
}
