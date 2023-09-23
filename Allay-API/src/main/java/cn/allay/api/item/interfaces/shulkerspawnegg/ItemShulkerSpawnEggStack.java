package cn.allay.api.item.interfaces.shulkerspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShulkerSpawnEggStack extends ItemStack {
    ItemType<ItemShulkerSpawnEggStack> SHULKER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemShulkerSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SHULKER_SPAWN_EGG)
            .build();
}
