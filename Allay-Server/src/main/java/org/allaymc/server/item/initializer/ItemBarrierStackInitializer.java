package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBarrierStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBarrierStackInitializer {
  static void init() {
    ItemTypes.BARRIER_TYPE = ItemTypeBuilder
            .builder(ItemBarrierStack.class)
            .vanillaItem(VanillaItemId.BARRIER)
            .build();
  }
}
