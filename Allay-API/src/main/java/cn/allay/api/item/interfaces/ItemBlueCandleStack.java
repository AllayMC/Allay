package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueCandleStack extends ItemStack {
    ItemType<ItemBlueCandleStack> BLUE_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemBlueCandleStack.class)
            .vanillaItem(VanillaItemId.BLUE_CANDLE)
            .build();
}
