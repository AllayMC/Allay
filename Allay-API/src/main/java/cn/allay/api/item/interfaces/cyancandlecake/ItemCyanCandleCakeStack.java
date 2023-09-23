package cn.allay.api.item.interfaces.cyancandlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanCandleCakeStack extends ItemStack {
    ItemType<ItemCyanCandleCakeStack> CYAN_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemCyanCandleCakeStack.class)
            .vanillaItem(VanillaItemId.CYAN_CANDLE_CAKE)
            .build();
}
