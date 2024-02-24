package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCrimsonFungusStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonFungusStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_FUNGUS_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonFungusStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_FUNGUS)
            .build();
  }
}
