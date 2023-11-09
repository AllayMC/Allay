package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAllaySpawnEggStack extends ItemStack {
  ItemType<ItemAllaySpawnEggStack> ALLAY_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemAllaySpawnEggStack.class)
          .vanillaItem(VanillaItemId.ALLAY_SPAWN_EGG)
          .build();
}
