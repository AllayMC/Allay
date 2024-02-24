package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemPinkShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.PINK_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemPinkShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.PINK_SHULKER_BOX)
            .build();
  }
}
