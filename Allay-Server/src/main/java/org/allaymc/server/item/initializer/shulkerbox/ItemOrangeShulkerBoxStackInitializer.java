package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemOrangeShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.ORANGE_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemOrangeShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.ORANGE_SHULKER_BOX)
            .build();
  }
}
