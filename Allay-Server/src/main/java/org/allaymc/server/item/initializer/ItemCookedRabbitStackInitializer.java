package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCookedRabbitStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedRabbitStackInitializer {
  static void init() {
    ItemTypes.COOKED_RABBIT_TYPE = ItemTypeBuilder
            .builder(ItemCookedRabbitStack.class)
            .vanillaItem(VanillaItemId.COOKED_RABBIT)
            .build();
  }
}
