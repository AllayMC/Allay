package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRabbitHideStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitHideStackInitializer {
  static void init() {
    ItemTypes.RABBIT_HIDE_TYPE = ItemTypeBuilder
            .builder(ItemRabbitHideStack.class)
            .vanillaItem(VanillaItemId.RABBIT_HIDE)
            .build();
  }
}
