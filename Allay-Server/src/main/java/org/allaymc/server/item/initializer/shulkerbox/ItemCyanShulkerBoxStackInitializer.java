package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemCyanShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.CYAN_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemCyanShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.CYAN_SHULKER_BOX)
            .build();
  }
}
