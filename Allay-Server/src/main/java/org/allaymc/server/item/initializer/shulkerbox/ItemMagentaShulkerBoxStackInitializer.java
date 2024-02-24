package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemMagentaShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemMagentaShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_SHULKER_BOX)
            .build();
  }
}
