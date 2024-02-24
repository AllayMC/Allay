package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLanternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLanternStackInitializer {
  static void init() {
    ItemTypes.LANTERN_TYPE = ItemTypeBuilder
            .builder(ItemLanternStack.class)
            .vanillaItem(VanillaItemId.LANTERN)
            .build();
  }
}
