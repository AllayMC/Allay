package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWeepingVinesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeepingVinesStackInitializer {
  static void init() {
    ItemTypes.WEEPING_VINES_TYPE = ItemTypeBuilder
            .builder(ItemWeepingVinesStack.class)
            .vanillaItem(VanillaItemId.WEEPING_VINES)
            .build();
  }
}
