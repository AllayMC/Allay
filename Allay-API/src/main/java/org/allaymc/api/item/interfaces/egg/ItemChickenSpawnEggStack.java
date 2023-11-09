package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChickenSpawnEggStack extends ItemStack {
  ItemType<ItemChickenSpawnEggStack> CHICKEN_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemChickenSpawnEggStack.class)
          .vanillaItem(VanillaItemId.CHICKEN_SPAWN_EGG)
          .build();
}
