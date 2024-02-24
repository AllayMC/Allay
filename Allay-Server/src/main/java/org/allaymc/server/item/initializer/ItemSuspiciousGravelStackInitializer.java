package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSuspiciousGravelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousGravelStackInitializer {
  static void init() {
    ItemTypes.SUSPICIOUS_GRAVEL_TYPE = ItemTypeBuilder
            .builder(ItemSuspiciousGravelStack.class)
            .vanillaItem(VanillaItemId.SUSPICIOUS_GRAVEL)
            .build();
  }
}
