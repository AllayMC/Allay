package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemWoodenButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenButtonStackInitializer {
  static void init() {
    ItemTypes.WOODEN_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemWoodenButtonStack.class)
            .vanillaItem(VanillaItemId.WOODEN_BUTTON)
            .build();
  }
}
