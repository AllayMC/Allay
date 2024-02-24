package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStrippedCrimsonHyphaeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCrimsonHyphaeStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_CRIMSON_HYPHAE_TYPE = ItemTypeBuilder
            .builder(ItemStrippedCrimsonHyphaeStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_CRIMSON_HYPHAE)
            .build();
  }
}
