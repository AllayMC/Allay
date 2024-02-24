package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSkeletonSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkeletonSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SKELETON_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSkeletonSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SKELETON_SPAWN_EGG)
            .build();
  }
}
