package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemGrayShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.GRAY_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemGrayShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.GRAY_SHULKER_BOX)
            .build();
  }
}
