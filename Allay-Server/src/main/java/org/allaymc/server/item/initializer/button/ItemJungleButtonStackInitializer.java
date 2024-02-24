package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemJungleButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleButtonStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemJungleButtonStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_BUTTON)
            .build();
  }
}
