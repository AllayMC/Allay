package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCaveVinesHeadWithBerriesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveVinesHeadWithBerriesStackInitializer {
  static void init() {
    ItemTypes.CAVE_VINES_HEAD_WITH_BERRIES_TYPE = ItemTypeBuilder
            .builder(ItemCaveVinesHeadWithBerriesStack.class)
            .vanillaItem(VanillaItemId.CAVE_VINES_HEAD_WITH_BERRIES)
            .build();
  }
}
