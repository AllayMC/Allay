package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSmallAmethystBudStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmallAmethystBudStackInitializer {
  static void init() {
    ItemTypes.SMALL_AMETHYST_BUD_TYPE = ItemTypeBuilder
            .builder(ItemSmallAmethystBudStack.class)
            .vanillaItem(VanillaItemId.SMALL_AMETHYST_BUD)
            .build();
  }
}
