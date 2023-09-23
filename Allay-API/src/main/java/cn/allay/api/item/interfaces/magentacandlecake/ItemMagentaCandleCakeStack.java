package cn.allay.api.item.interfaces.magentacandlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaCandleCakeStack extends ItemStack {
    ItemType<ItemMagentaCandleCakeStack> MAGENTA_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemMagentaCandleCakeStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_CANDLE_CAKE)
            .build();
}
