package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemDonkeySpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDonkeySpawnEggStackInitializer {
  static void init() {
    ItemTypes.DONKEY_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemDonkeySpawnEggStack.class)
            .vanillaItem(VanillaItemId.DONKEY_SPAWN_EGG)
            .build();
  }
}
