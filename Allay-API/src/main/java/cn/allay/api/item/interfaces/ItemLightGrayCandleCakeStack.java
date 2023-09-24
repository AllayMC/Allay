package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayCandleCakeStack extends ItemStack {
    ItemType<ItemLightGrayCandleCakeStack> LIGHT_GRAY_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayCandleCakeStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CANDLE_CAKE)
            .build();
}
