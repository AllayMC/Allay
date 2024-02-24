package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.SHULKER_BOX)
            .build();
  }
}
