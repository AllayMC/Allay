package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCandleCakeStack extends ItemStack {
    ItemType<ItemRedCandleCakeStack> RED_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemRedCandleCakeStack.class)
            .vanillaItem(VanillaItemId.RED_CANDLE_CAKE)
            .build();
}
