package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
