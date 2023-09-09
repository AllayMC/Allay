package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedNyliumStack extends ItemStack {
    ItemType<ItemWarpedNyliumStack> WARPED_NYLIUM_TYPE = ItemTypeBuilder
            .builder(ItemWarpedNyliumStack.class)
            .vanillaItem(VanillaItemId.WARPED_NYLIUM)
            .build();
}
