package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemBreezeSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBreezeSpawnEggStackInitializer {
  static void init() {
    ItemTypes.BREEZE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemBreezeSpawnEggStack.class)
            .vanillaItem(VanillaItemId.BREEZE_SPAWN_EGG)
            .build();
  }
}
