package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStainedHardenedClayStack extends ItemStack {
    ItemType<ItemStainedHardenedClayStack> STAINED_HARDENED_CLAY_TYPE = ItemTypeBuilder
            .builder(ItemStainedHardenedClayStack.class)
            .vanillaItem(VanillaItemId.STAINED_HARDENED_CLAY)
            .build();
}
