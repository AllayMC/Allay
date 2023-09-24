package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueCandleCakeStack extends ItemStack {
    ItemType<ItemLightBlueCandleCakeStack> LIGHT_BLUE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueCandleCakeStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CANDLE_CAKE)
            .build();
}
