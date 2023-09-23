package cn.allay.api.item.interfaces.bleach;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBleachStack extends ItemStack {
    ItemType<ItemBleachStack> BLEACH_TYPE = ItemTypeBuilder
            .builder(ItemBleachStack.class)
            .vanillaItem(VanillaItemId.BLEACH)
            .build();
}
