package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGildedBlackstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGildedBlackstoneStackInitializer {
  static void init() {
    ItemTypes.GILDED_BLACKSTONE_TYPE = ItemTypeBuilder
            .builder(ItemGildedBlackstoneStack.class)
            .vanillaItem(VanillaItemId.GILDED_BLACKSTONE)
            .build();
  }
}
