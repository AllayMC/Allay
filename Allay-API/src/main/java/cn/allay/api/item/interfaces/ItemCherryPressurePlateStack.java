package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryPressurePlateStack extends ItemStack {
    ItemType<ItemCherryPressurePlateStack> CHERRY_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemCherryPressurePlateStack.class)
            .vanillaItem(VanillaItemId.CHERRY_PRESSURE_PLATE)
            .build();
}
