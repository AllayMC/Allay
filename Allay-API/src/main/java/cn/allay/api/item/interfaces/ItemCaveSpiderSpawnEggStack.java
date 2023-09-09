package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveSpiderSpawnEggStack extends ItemStack {
    ItemType<ItemCaveSpiderSpawnEggStack> CAVE_SPIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCaveSpiderSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CAVE_SPIDER_SPAWN_EGG)
            .build();
}
