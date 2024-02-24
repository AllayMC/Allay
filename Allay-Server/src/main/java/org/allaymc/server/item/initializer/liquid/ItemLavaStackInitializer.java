package org.allaymc.server.item.initializer.liquid;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.liquid.ItemLavaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLavaStackInitializer {
  static void init() {
    ItemTypes.LAVA_TYPE = ItemTypeBuilder
            .builder(ItemLavaStack.class)
            .vanillaItem(VanillaItemId.LAVA)
            .build();
  }
}
