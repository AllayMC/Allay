package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemWhiteShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.WHITE_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemWhiteShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.WHITE_SHULKER_BOX)
            .build();
  }
}
