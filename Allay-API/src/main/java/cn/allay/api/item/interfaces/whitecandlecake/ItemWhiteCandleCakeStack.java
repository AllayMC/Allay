package cn.allay.api.item.interfaces.whitecandlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteCandleCakeStack extends ItemStack {
    ItemType<ItemWhiteCandleCakeStack> WHITE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteCandleCakeStack.class)
            .vanillaItem(VanillaItemId.WHITE_CANDLE_CAKE)
            .build();
}
