package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFrogSpawnStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFrogSpawnStackInitializer {
  static void init() {
    ItemTypes.FROG_SPAWN_TYPE = ItemTypeBuilder
            .builder(ItemFrogSpawnStack.class)
            .vanillaItem(VanillaItemId.FROG_SPAWN)
            .build();
  }
}
