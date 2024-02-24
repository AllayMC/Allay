package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRecoveryCompassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRecoveryCompassStackInitializer {
  static void init() {
    ItemTypes.RECOVERY_COMPASS_TYPE = ItemTypeBuilder
            .builder(ItemRecoveryCompassStack.class)
            .vanillaItem(VanillaItemId.RECOVERY_COMPASS)
            .build();
  }
}
