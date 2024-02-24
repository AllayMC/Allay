package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRepeaterStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRepeaterStackInitializer {
  static void init() {
    ItemTypes.REPEATER_TYPE = ItemTypeBuilder
            .builder(ItemRepeaterStack.class)
            .vanillaItem(VanillaItemId.REPEATER)
            .build();
  }
}
