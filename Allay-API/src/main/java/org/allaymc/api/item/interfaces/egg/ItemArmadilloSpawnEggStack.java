package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArmadilloSpawnEggStack extends ItemStack {
  ItemType<ItemArmadilloSpawnEggStack> ARMADILLO_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemArmadilloSpawnEggStack.class)
          .vanillaItem(VanillaItemId.ARMADILLO_SPAWN_EGG)
          .build();
}
