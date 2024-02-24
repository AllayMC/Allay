package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemPurpleShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.PURPLE_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemPurpleShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.PURPLE_SHULKER_BOX)
            .build();
  }
}
