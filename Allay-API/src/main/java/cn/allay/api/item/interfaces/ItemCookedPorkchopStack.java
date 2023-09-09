package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedPorkchopStack extends ItemStack {
    ItemType<ItemCookedPorkchopStack> COOKED_PORKCHOP_TYPE = ItemTypeBuilder
            .builder(ItemCookedPorkchopStack.class)
            .vanillaItem(VanillaItemId.COOKED_PORKCHOP)
            .build();
}
