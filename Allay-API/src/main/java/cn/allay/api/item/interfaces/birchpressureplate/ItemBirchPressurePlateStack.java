package cn.allay.api.item.interfaces.birchpressureplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchPressurePlateStack extends ItemStack {
    ItemType<ItemBirchPressurePlateStack> BIRCH_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemBirchPressurePlateStack.class)
            .vanillaItem(VanillaItemId.BIRCH_PRESSURE_PLATE)
            .build();
}
