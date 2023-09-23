package cn.allay.api.item.interfaces.greencandlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenCandleCakeStack extends ItemStack {
    ItemType<ItemGreenCandleCakeStack> GREEN_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemGreenCandleCakeStack.class)
            .vanillaItem(VanillaItemId.GREEN_CANDLE_CAKE)
            .build();
}
