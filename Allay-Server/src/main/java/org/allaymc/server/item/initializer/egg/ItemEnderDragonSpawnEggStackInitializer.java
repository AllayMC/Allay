package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemEnderDragonSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderDragonSpawnEggStackInitializer {
  static void init() {
    ItemTypes.ENDER_DRAGON_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemEnderDragonSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ENDER_DRAGON_SPAWN_EGG)
            .build();
  }
}
