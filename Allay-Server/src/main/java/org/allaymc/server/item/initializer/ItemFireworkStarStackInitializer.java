package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFireworkStarStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireworkStarStackInitializer {
  static void init() {
    ItemTypes.FIREWORK_STAR_TYPE = ItemTypeBuilder
            .builder(ItemFireworkStarStack.class)
            .vanillaItem(VanillaItemId.FIREWORK_STAR)
            .build();
  }
}
