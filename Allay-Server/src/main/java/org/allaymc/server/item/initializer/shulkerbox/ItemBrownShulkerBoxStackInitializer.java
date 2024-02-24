package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemBrownShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.BROWN_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemBrownShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.BROWN_SHULKER_BOX)
            .build();
  }
}
