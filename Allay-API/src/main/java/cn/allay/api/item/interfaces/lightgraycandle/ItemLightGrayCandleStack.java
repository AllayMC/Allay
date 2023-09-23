package cn.allay.api.item.interfaces.lightgraycandle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayCandleStack extends ItemStack {
    ItemType<ItemLightGrayCandleStack> LIGHT_GRAY_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayCandleStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CANDLE)
            .build();
}
