package cn.allay.api.item.interfaces.water;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaterStack extends ItemStack {
    ItemType<ItemWaterStack> WATER_TYPE = ItemTypeBuilder
            .builder(ItemWaterStack.class)
            .vanillaItem(VanillaItemId.WATER)
            .build();
}
