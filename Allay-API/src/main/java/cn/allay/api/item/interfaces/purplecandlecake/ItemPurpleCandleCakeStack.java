package cn.allay.api.item.interfaces.purplecandlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleCandleCakeStack extends ItemStack {
    ItemType<ItemPurpleCandleCakeStack> PURPLE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemPurpleCandleCakeStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CANDLE_CAKE)
            .build();
}
