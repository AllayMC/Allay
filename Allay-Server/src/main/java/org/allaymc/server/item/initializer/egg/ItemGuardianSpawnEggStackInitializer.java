package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemGuardianSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGuardianSpawnEggStackInitializer {
  static void init() {
    ItemTypes.GUARDIAN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemGuardianSpawnEggStack.class)
            .vanillaItem(VanillaItemId.GUARDIAN_SPAWN_EGG)
            .build();
  }
}
