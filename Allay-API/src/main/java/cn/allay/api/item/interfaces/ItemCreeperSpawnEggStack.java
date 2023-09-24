package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCreeperSpawnEggStack extends ItemStack {
    ItemType<ItemCreeperSpawnEggStack> CREEPER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCreeperSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CREEPER_SPAWN_EGG)
            .build();
}
