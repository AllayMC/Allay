package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrewingStandStack extends ItemStack {
    ItemType<ItemBrewingStandStack> BREWING_STAND_TYPE = ItemTypeBuilder
            .builder(ItemBrewingStandStack.class)
            .vanillaItem(VanillaItemId.BREWING_STAND)
            .build();
}
