package cn.allay.api.item.interfaces.purplecandle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleCandleStack extends ItemStack {
    ItemType<ItemPurpleCandleStack> PURPLE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemPurpleCandleStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CANDLE)
            .build();
}
