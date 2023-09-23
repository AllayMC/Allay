package cn.allay.api.item.interfaces.zombiespawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemZombieSpawnEggStack extends ItemStack {
    ItemType<ItemZombieSpawnEggStack> ZOMBIE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemZombieSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ZOMBIE_SPAWN_EGG)
            .build();
}
