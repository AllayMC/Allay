package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemWarpedButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedButtonStackInitializer {
  static void init() {
    ItemTypes.WARPED_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemWarpedButtonStack.class)
            .vanillaItem(VanillaItemId.WARPED_BUTTON)
            .build();
  }
}
