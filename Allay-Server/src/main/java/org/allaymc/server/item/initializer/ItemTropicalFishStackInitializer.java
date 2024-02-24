package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTropicalFishStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTropicalFishStackInitializer {
  static void init() {
    ItemTypes.TROPICAL_FISH_TYPE = ItemTypeBuilder
            .builder(ItemTropicalFishStack.class)
            .vanillaItem(VanillaItemId.TROPICAL_FISH)
            .build();
  }
}
