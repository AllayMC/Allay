package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemPurpleWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleWoolStackInitializer {
  static void init() {
    ItemTypes.PURPLE_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemPurpleWoolStack.class)
            .vanillaItem(VanillaItemId.PURPLE_WOOL)
            .build();
  }
}
