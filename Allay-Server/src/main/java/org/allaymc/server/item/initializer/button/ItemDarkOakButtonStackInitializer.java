package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemDarkOakButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakButtonStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakButtonStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_BUTTON)
            .build();
  }
}
