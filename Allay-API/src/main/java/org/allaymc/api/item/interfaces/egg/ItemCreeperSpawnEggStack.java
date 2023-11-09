package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
