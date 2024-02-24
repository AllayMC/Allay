package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTntStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTntStackInitializer {
  static void init() {
    ItemTypes.TNT_TYPE = ItemTypeBuilder
            .builder(ItemTntStack.class)
            .vanillaItem(VanillaItemId.TNT)
            .build();
  }
}
