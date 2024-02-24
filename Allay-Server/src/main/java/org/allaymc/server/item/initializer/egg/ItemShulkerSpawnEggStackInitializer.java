package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemShulkerSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShulkerSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SHULKER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemShulkerSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SHULKER_SPAWN_EGG)
            .build();
  }
}
