package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemBrownWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownWoolStackInitializer {
  static void init() {
    ItemTypes.BROWN_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemBrownWoolStack.class)
            .vanillaItem(VanillaItemId.BROWN_WOOL)
            .build();
  }
}
