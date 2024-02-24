package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemPinkWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkWoolStackInitializer {
  static void init() {
    ItemTypes.PINK_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemPinkWoolStack.class)
            .vanillaItem(VanillaItemId.PINK_WOOL)
            .build();
  }
}
