package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemLightGrayShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_SHULKER_BOX)
            .build();
  }
}
