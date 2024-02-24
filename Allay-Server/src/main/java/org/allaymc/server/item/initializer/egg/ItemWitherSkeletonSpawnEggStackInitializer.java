package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemWitherSkeletonSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWitherSkeletonSpawnEggStackInitializer {
  static void init() {
    ItemTypes.WITHER_SKELETON_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemWitherSkeletonSpawnEggStack.class)
            .vanillaItem(VanillaItemId.WITHER_SKELETON_SPAWN_EGG)
            .build();
  }
}
