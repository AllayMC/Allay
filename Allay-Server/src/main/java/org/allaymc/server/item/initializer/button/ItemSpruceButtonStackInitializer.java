package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemSpruceButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceButtonStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemSpruceButtonStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_BUTTON)
            .build();
  }
}
