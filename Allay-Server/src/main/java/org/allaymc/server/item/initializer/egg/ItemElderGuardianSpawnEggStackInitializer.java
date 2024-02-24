package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemElderGuardianSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElderGuardianSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ELDER_GUARDIAN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemElderGuardianSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ELDER_GUARDIAN_SPAWN_EGG)
            .build();
  }
}
