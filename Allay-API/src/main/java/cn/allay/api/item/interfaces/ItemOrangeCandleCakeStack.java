package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeCandleCakeStack extends ItemStack {
    ItemType<ItemOrangeCandleCakeStack> ORANGE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemOrangeCandleCakeStack.class)
            .vanillaItem(VanillaItemId.ORANGE_CANDLE_CAKE)
            .build();
}
