package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemBlackShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.BLACK_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemBlackShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.BLACK_SHULKER_BOX)
            .build();
  }
}
