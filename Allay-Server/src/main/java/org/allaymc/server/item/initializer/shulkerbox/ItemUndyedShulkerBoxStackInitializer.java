package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemUndyedShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUndyedShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.UNDYED_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemUndyedShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.UNDYED_SHULKER_BOX)
            .build();
  }
}
