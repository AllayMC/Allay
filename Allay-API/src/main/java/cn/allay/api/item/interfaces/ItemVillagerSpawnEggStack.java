package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVillagerSpawnEggStack extends ItemStack {
    ItemType<ItemVillagerSpawnEggStack> VILLAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemVillagerSpawnEggStack.class)
            .vanillaItem(VanillaItemId.VILLAGER_SPAWN_EGG)
            .build();
}
