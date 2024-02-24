package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemComposterStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemComposterStackInitializer {
  static void init() {
    ItemTypes.COMPOSTER_TYPE = ItemTypeBuilder
            .builder(ItemComposterStack.class)
            .vanillaItem(VanillaItemId.COMPOSTER)
            .build();
  }
}
