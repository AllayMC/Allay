package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSkeletonHorseSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkeletonHorseSpawnEggStackInitializer {
  static void init() {
    ItemTypes.SKELETON_HORSE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSkeletonHorseSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SKELETON_HORSE_SPAWN_EGG)
            .build();
  }
}
