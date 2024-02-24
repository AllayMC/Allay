package org.allaymc.server.item.initializer.liquid;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.liquid.ItemFlowingLavaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowingLavaStackInitializer {
  static void init() {
    ItemTypes.FLOWING_LAVA_TYPE = ItemTypeBuilder
            .builder(ItemFlowingLavaStack.class)
            .vanillaItem(VanillaItemId.FLOWING_LAVA)
            .build();
  }
}
