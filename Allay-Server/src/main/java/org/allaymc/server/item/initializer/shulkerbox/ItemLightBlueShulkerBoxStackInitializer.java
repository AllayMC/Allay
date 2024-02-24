package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemLightBlueShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_SHULKER_BOX)
            .build();
  }
}
