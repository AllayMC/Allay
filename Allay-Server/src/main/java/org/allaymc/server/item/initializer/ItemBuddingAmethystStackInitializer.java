package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBuddingAmethystStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBuddingAmethystStackInitializer {
  static void init() {
    ItemTypes.BUDDING_AMETHYST_TYPE = ItemTypeBuilder
            .builder(ItemBuddingAmethystStack.class)
            .vanillaItem(VanillaItemId.BUDDING_AMETHYST)
            .build();
  }
}
