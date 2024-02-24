package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemStoneButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneButtonStackInitializer {
  static void init() {
    ItemTypes.STONE_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemStoneButtonStack.class)
            .vanillaItem(VanillaItemId.STONE_BUTTON)
            .build();
  }
}
