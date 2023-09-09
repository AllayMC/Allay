package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangrovePressurePlateStack extends ItemStack {
    ItemType<ItemMangrovePressurePlateStack> MANGROVE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemMangrovePressurePlateStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_PRESSURE_PLATE)
            .build();
}
