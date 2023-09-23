package cn.allay.api.item.interfaces.stonepressureplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStonePressurePlateStack extends ItemStack {
    ItemType<ItemStonePressurePlateStack> STONE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemStonePressurePlateStack.class)
            .vanillaItem(VanillaItemId.STONE_PRESSURE_PLATE)
            .build();
}
