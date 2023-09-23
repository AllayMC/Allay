package cn.allay.api.item.interfaces.woodenpressureplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenPressurePlateStack extends ItemStack {
    ItemType<ItemWoodenPressurePlateStack> WOODEN_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemWoodenPressurePlateStack.class)
            .vanillaItem(VanillaItemId.WOODEN_PRESSURE_PLATE)
            .build();
}
