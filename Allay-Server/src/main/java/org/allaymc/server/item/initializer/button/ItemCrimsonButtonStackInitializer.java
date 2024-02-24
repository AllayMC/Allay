package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemCrimsonButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonButtonStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonButtonStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_BUTTON)
            .build();
  }
}
