package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemGreenShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.GREEN_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemGreenShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.GREEN_SHULKER_BOX)
            .build();
  }
}
