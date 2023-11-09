package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTadpoleSpawnEggStack extends ItemStack {
  ItemType<ItemTadpoleSpawnEggStack> TADPOLE_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemTadpoleSpawnEggStack.class)
          .vanillaItem(VanillaItemId.TADPOLE_SPAWN_EGG)
          .build();
}
