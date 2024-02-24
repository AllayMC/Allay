package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemMagmaCubeSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagmaCubeSpawnEggStackInitializer {
  static void init() {
    ItemTypes.MAGMA_CUBE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemMagmaCubeSpawnEggStack.class)
            .vanillaItem(VanillaItemId.MAGMA_CUBE_SPAWN_EGG)
            .build();
  }
}
