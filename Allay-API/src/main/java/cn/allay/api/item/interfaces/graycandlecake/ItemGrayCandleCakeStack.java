package cn.allay.api.item.interfaces.graycandlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayCandleCakeStack extends ItemStack {
    ItemType<ItemGrayCandleCakeStack> GRAY_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemGrayCandleCakeStack.class)
            .vanillaItem(VanillaItemId.GRAY_CANDLE_CAKE)
            .build();
}
