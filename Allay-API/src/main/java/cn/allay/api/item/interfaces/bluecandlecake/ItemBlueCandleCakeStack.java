package cn.allay.api.item.interfaces.bluecandlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueCandleCakeStack extends ItemStack {
    ItemType<ItemBlueCandleCakeStack> BLUE_CANDLE_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemBlueCandleCakeStack.class)
            .vanillaItem(VanillaItemId.BLUE_CANDLE_CAKE)
            .build();
}
