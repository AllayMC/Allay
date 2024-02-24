package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemMangroveButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveButtonStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemMangroveButtonStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_BUTTON)
            .build();
  }
}
