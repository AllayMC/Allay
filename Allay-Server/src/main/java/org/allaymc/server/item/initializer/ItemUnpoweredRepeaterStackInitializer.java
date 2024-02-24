package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemUnpoweredRepeaterStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnpoweredRepeaterStackInitializer {
  static void init() {
    ItemTypes.UNPOWERED_REPEATER_TYPE = ItemTypeBuilder
            .builder(ItemUnpoweredRepeaterStack.class)
            .vanillaItem(VanillaItemId.UNPOWERED_REPEATER)
            .build();
  }
}
