package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakPressurePlateStack extends ItemStack {
    ItemType<ItemDarkOakPressurePlateStack> DARK_OAK_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakPressurePlateStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_PRESSURE_PLATE)
            .build();
}
