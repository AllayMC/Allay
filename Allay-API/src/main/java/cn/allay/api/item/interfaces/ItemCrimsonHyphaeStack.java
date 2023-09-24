package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonHyphaeStack extends ItemStack {
    ItemType<ItemCrimsonHyphaeStack> CRIMSON_HYPHAE_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonHyphaeStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_HYPHAE)
            .build();
}
