package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMobSpawnerStack extends ItemStack {
  ItemType<ItemMobSpawnerStack> MOB_SPAWNER_TYPE = ItemTypeBuilder
          .builder(ItemMobSpawnerStack.class)
          .vanillaItem(VanillaItemId.MOB_SPAWNER)
          .build();
}
