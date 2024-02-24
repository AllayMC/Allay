package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSuspiciousStewStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousStewStackInitializer {
  static void init() {
    ItemTypes.SUSPICIOUS_STEW_TYPE = ItemTypeBuilder
            .builder(ItemSuspiciousStewStack.class)
            .vanillaItem(VanillaItemId.SUSPICIOUS_STEW)
            .build();
  }
}
