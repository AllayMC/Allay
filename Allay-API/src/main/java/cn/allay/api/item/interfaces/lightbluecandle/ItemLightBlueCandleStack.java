package cn.allay.api.item.interfaces.lightbluecandle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueCandleStack extends ItemStack {
    ItemType<ItemLightBlueCandleStack> LIGHT_BLUE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueCandleStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CANDLE)
            .build();
}
