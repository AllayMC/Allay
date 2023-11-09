package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnifferSpawnEggStack extends ItemStack {
  ItemType<ItemSnifferSpawnEggStack> SNIFFER_SPAWN_EGG_TYPE = ItemTypeBuilder
          .builder(ItemSnifferSpawnEggStack.class)
          .vanillaItem(VanillaItemId.SNIFFER_SPAWN_EGG)
          .build();
}
