package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRabbitFootStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitFootStackInitializer {
  static void init() {
    ItemTypes.RABBIT_FOOT_TYPE = ItemTypeBuilder
            .builder(ItemRabbitFootStack.class)
            .vanillaItem(VanillaItemId.RABBIT_FOOT)
            .build();
  }
}
