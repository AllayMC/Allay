package cn.allay.api.item.interfaces.warpedhyphae;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedHyphaeStack extends ItemStack {
    ItemType<ItemWarpedHyphaeStack> WARPED_HYPHAE_TYPE = ItemTypeBuilder
            .builder(ItemWarpedHyphaeStack.class)
            .vanillaItem(VanillaItemId.WARPED_HYPHAE)
            .build();
}
