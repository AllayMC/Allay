package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemEndermiteSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndermiteSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ENDERMITE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemEndermiteSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ENDERMITE_SPAWN_EGG)
            .build();
  }
}
