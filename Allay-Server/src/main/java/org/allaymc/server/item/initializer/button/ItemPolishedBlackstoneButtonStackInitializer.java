package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemPolishedBlackstoneButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneButtonStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneButtonStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BUTTON)
            .build();
  }
}
