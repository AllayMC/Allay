package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemOcelotSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOcelotSpawnEggStackInitializer {
  static void init() {
    ItemTypes.OCELOT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemOcelotSpawnEggStack.class)
            .vanillaItem(VanillaItemId.OCELOT_SPAWN_EGG)
            .build();
  }
}
