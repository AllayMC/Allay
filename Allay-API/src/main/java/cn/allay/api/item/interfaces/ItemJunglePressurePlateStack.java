package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJunglePressurePlateStack extends ItemStack {
    ItemType<ItemJunglePressurePlateStack> JUNGLE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemJunglePressurePlateStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_PRESSURE_PLATE)
            .build();
}
