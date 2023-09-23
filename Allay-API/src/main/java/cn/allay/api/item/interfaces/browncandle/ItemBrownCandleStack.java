package cn.allay.api.item.interfaces.browncandle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownCandleStack extends ItemStack {
    ItemType<ItemBrownCandleStack> BROWN_CANDLE_TYPE = ItemTypeBuilder
            .builder(ItemBrownCandleStack.class)
            .vanillaItem(VanillaItemId.BROWN_CANDLE)
            .build();
}
