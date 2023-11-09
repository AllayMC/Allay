package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemZombieVillagerSpawnEggStack extends ItemStack {
  ItemType<ItemZombieVillagerSpawnEggStack> ZOMBIE_VILLAGER_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemZombieVillagerSpawnEggStack.class)
          .vanillaItem(VanillaItemId.ZOMBIE_VILLAGER_SPAWN_EGG)
          .build();
}
