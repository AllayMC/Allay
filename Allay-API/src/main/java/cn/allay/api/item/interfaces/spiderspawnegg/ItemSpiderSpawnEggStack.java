package cn.allay.api.item.interfaces.spiderspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpiderSpawnEggStack extends ItemStack {
    ItemType<ItemSpiderSpawnEggStack> SPIDER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSpiderSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SPIDER_SPAWN_EGG)
            .build();
}
