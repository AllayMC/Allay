package cn.allay.api.item.interfaces.pinkcandlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCandleCakeStack extends ItemStack {
    ItemType<ItemPinkCandleCakeStack> PINK_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemPinkCandleCakeStack.class)
            .vanillaItem(VanillaItemId.PINK_CANDLE_CAKE)
            .build();
}
