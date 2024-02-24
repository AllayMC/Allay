package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFireworkRocketStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireworkRocketStackInitializer {
  static void init() {
    ItemTypes.FIREWORK_ROCKET_TYPE = ItemTypeBuilder
            .builder(ItemFireworkRocketStack.class)
            .vanillaItem(VanillaItemId.FIREWORK_ROCKET)
            .build();
  }
}
