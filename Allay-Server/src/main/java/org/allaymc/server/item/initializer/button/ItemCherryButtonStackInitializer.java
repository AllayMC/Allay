package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemCherryButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryButtonStackInitializer {
  static void init() {
    ItemTypes.CHERRY_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemCherryButtonStack.class)
            .vanillaItem(VanillaItemId.CHERRY_BUTTON)
            .build();
  }
}
