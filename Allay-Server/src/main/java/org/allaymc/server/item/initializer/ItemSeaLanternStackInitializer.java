package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSeaLanternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSeaLanternStackInitializer {
  static void init() {
    ItemTypes.SEA_LANTERN_TYPE = ItemTypeBuilder
            .builder(ItemSeaLanternStack.class)
            .vanillaItem(VanillaItemId.SEA_LANTERN)
            .build();
  }
}
