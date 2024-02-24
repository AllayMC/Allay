package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTargetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTargetStackInitializer {
  static void init() {
    ItemTypes.TARGET_TYPE = ItemTypeBuilder
            .builder(ItemTargetStack.class)
            .vanillaItem(VanillaItemId.TARGET)
            .build();
  }
}
