package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNameTagStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNameTagStackInitializer {
  static void init() {
    ItemTypes.NAME_TAG_TYPE = ItemTypeBuilder
            .builder(ItemNameTagStack.class)
            .vanillaItem(VanillaItemId.NAME_TAG)
            .build();
  }
}
