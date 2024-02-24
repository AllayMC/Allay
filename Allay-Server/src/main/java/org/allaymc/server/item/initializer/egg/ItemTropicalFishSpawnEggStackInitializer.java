package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemTropicalFishSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTropicalFishSpawnEggStackInitializer {
  static void init() {
    ItemTypes.TROPICAL_FISH_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTropicalFishSpawnEggStack.class)
            .vanillaItem(VanillaItemId.TROPICAL_FISH_SPAWN_EGG)
            .build();
  }
}
