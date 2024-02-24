package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDecoratedPotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDecoratedPotStackInitializer {
  static void init() {
    ItemTypes.DECORATED_POT_TYPE = ItemTypeBuilder
            .builder(ItemDecoratedPotStack.class)
            .vanillaItem(VanillaItemId.DECORATED_POT)
            .build();
  }
}
