package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenCarrotStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenCarrotStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_CARROT_TYPE = ItemTypeBuilder
            .builder(ItemGoldenCarrotStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_CARROT)
            .build();
  }
}
