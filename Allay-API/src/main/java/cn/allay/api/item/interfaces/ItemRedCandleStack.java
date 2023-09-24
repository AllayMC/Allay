package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCandleStack extends ItemStack {
    ItemType<ItemRedCandleStack> RED_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemRedCandleStack.class)
            .vanillaItem(VanillaItemId.RED_CANDLE)
            .build();
}
