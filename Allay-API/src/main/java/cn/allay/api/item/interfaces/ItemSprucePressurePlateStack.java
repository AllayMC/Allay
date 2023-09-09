package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSprucePressurePlateStack extends ItemStack {
    ItemType<ItemSprucePressurePlateStack> SPRUCE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemSprucePressurePlateStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_PRESSURE_PLATE)
            .build();
}
