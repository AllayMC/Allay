package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemYellowShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.YELLOW_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemYellowShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.YELLOW_SHULKER_BOX)
            .build();
  }
}
