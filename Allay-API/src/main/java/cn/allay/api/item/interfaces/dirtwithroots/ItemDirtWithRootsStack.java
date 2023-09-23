package cn.allay.api.item.interfaces.dirtwithroots;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDirtWithRootsStack extends ItemStack {
    ItemType<ItemDirtWithRootsStack> DIRT_WITH_ROOTS_TYPE = ItemTypeBuilder
            .builder(ItemDirtWithRootsStack.class)
            .vanillaItem(VanillaItemId.DIRT_WITH_ROOTS)
            .build();
}
