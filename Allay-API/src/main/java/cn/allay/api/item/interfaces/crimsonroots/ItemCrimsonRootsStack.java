package cn.allay.api.item.interfaces.crimsonroots;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonRootsStack extends ItemStack {
    ItemType<ItemCrimsonRootsStack> CRIMSON_ROOTS_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonRootsStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_ROOTS)
            .build();
}
