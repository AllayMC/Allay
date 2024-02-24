package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSuspiciousSandStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousSandStackInitializer {
  static void init() {
    ItemTypes.SUSPICIOUS_SAND_TYPE = ItemTypeBuilder
            .builder(ItemSuspiciousSandStack.class)
            .vanillaItem(VanillaItemId.SUSPICIOUS_SAND)
            .build();
  }
}
