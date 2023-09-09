package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaCandleStack extends ItemStack {
    ItemType<ItemMagentaCandleStack> MAGENTA_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemMagentaCandleStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_CANDLE)
            .build();
}
