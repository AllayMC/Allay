package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFrogSpawnStack extends ItemStack {
  ItemType<ItemFrogSpawnStack> FROG_SPAWN_TYPE = ItemTypeBuilder
          .builder(ItemFrogSpawnStack.class)
          .vanillaItem(VanillaItemId.FROG_SPAWN)
          .build();
}
