package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCarrotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarrotStackInitializer {
  static void init() {
    ItemTypes.CARROT_TYPE = ItemTypeBuilder
            .builder(ItemCarrotStack.class)
            .vanillaItem(VanillaItemId.CARROT)
            .build();
  }
}
