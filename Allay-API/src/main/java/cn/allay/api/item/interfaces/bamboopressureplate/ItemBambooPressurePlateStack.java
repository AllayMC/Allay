package cn.allay.api.item.interfaces.bamboopressureplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooPressurePlateStack extends ItemStack {
    ItemType<ItemBambooPressurePlateStack> BAMBOO_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemBambooPressurePlateStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_PRESSURE_PLATE)
            .build();
}
