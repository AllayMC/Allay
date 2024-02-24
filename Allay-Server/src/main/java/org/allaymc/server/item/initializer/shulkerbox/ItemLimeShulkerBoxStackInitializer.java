package org.allaymc.server.item.initializer.shulkerbox;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.shulkerbox.ItemLimeShulkerBoxStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeShulkerBoxStackInitializer {
  static void init() {
    ItemTypes.LIME_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemLimeShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.LIME_SHULKER_BOX)
            .build();
  }
}
