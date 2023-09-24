package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedWarpedHyphaeStack extends ItemStack {
    ItemType<ItemStrippedWarpedHyphaeStack> STRIPPED_WARPED_HYPHAE_TYPE = ItemTypeBuilder
            .builder(ItemStrippedWarpedHyphaeStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_WARPED_HYPHAE)
            .build();
}
