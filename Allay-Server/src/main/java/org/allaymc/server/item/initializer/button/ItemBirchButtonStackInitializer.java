package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemBirchButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchButtonStackInitializer {
  static void init() {
    ItemTypes.BIRCH_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemBirchButtonStack.class)
            .vanillaItem(VanillaItemId.BIRCH_BUTTON)
            .build();
  }
}
