package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBatSpawnEggStack extends ItemStack {
  ItemType<ItemBatSpawnEggStack> BAT_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemBatSpawnEggStack.class)
          .vanillaItem(VanillaItemId.BAT_SPAWN_EGG)
          .build();
}
