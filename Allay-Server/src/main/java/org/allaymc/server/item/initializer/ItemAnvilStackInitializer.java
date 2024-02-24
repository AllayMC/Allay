package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAnvilStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAnvilStackInitializer {
  static void init() {
    ItemTypes.ANVIL_TYPE = ItemTypeBuilder
            .builder(ItemAnvilStack.class)
            .vanillaItem(VanillaItemId.ANVIL)
            .build();
  }
}
