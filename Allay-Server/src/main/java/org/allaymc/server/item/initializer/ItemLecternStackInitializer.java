package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLecternStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLecternStackInitializer {
  static void init() {
    ItemTypes.LECTERN_TYPE = ItemTypeBuilder
            .builder(ItemLecternStack.class)
            .vanillaItem(VanillaItemId.LECTERN)
            .build();
  }
}
