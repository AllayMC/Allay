package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTwistingVinesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTwistingVinesStackInitializer {
  static void init() {
    ItemTypes.TWISTING_VINES_TYPE = ItemTypeBuilder
            .builder(ItemTwistingVinesStack.class)
            .vanillaItem(VanillaItemId.TWISTING_VINES)
            .build();
  }
}
