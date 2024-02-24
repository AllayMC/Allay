package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMediumAmethystBudStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMediumAmethystBudStackInitializer {
  static void init() {
    ItemTypes.MEDIUM_AMETHYST_BUD_TYPE = ItemTypeBuilder
            .builder(ItemMediumAmethystBudStack.class)
            .vanillaItem(VanillaItemId.MEDIUM_AMETHYST_BUD)
            .build();
  }
}
