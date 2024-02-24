package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemCyanWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanWoolStackInitializer {
  static void init() {
    ItemTypes.CYAN_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemCyanWoolStack.class)
            .vanillaItem(VanillaItemId.CYAN_WOOL)
            .build();
  }
}
