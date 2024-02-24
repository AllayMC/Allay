package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRabbitStewStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitStewStackInitializer {
  static void init() {
    ItemTypes.RABBIT_STEW_TYPE = ItemTypeBuilder
            .builder(ItemRabbitStewStack.class)
            .vanillaItem(VanillaItemId.RABBIT_STEW)
            .build();
  }
}
