package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSoulLanternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulLanternStackInitializer {
  static void init() {
    ItemTypes.SOUL_LANTERN_TYPE = ItemTypeBuilder
            .builder(ItemSoulLanternStack.class)
            .vanillaItem(VanillaItemId.SOUL_LANTERN)
            .build();
  }
}
