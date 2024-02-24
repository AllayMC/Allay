package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetherreactorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherreactorStackInitializer {
  static void init() {
    ItemTypes.NETHERREACTOR_TYPE = ItemTypeBuilder
            .builder(ItemNetherreactorStack.class)
            .vanillaItem(VanillaItemId.NETHERREACTOR)
            .build();
  }
}
