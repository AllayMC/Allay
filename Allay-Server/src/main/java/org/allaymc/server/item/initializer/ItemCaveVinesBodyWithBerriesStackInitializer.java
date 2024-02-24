package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCaveVinesBodyWithBerriesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveVinesBodyWithBerriesStackInitializer {
  static void init() {
    ItemTypes.CAVE_VINES_BODY_WITH_BERRIES_TYPE = ItemTypeBuilder
            .builder(ItemCaveVinesBodyWithBerriesStack.class)
            .vanillaItem(VanillaItemId.CAVE_VINES_BODY_WITH_BERRIES)
            .build();
  }
}
