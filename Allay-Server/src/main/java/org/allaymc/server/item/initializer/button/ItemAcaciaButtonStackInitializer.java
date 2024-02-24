package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemAcaciaButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaButtonStackInitializer {
  static void init() {
    ItemTypes.ACACIA_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaButtonStack.class)
            .vanillaItem(VanillaItemId.ACACIA_BUTTON)
            .build();
  }
}
