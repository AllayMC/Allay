package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLargeAmethystBudStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLargeAmethystBudStackInitializer {
  static void init() {
    ItemTypes.LARGE_AMETHYST_BUD_TYPE = ItemTypeBuilder
            .builder(ItemLargeAmethystBudStack.class)
            .vanillaItem(VanillaItemId.LARGE_AMETHYST_BUD)
            .build();
  }
}
