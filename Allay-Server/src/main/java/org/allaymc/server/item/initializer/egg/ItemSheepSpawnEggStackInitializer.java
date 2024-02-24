package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSheepSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSheepSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SHEEP_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSheepSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SHEEP_SPAWN_EGG)
            .build();
  }
}
