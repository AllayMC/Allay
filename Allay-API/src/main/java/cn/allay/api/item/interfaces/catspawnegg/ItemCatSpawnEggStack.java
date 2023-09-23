package cn.allay.api.item.interfaces.catspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCatSpawnEggStack extends ItemStack {
    ItemType<ItemCatSpawnEggStack> CAT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCatSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CAT_SPAWN_EGG)
            .build();
}
