package org.allaymc.server.item.initializer.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.button.ItemBambooButtonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooButtonStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemBambooButtonStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_BUTTON)
            .build();
  }
}
