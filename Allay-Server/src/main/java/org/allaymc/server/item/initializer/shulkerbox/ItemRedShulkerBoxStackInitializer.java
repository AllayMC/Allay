package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemRedShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.RED_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemRedShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.RED_SHULKER_BOX)
            .build();
  }
}
