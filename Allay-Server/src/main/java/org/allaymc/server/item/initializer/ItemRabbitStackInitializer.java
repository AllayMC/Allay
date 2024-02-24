package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRabbitStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitStackInitializer {
  static void init() {
    ItemTypes.RABBIT_TYPE = ItemTypeBuilder
            .builder(ItemRabbitStack.class)
            .vanillaItem(VanillaItemId.RABBIT)
            .build();
  }
}
