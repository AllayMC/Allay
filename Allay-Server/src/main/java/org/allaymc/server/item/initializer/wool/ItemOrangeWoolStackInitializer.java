package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemOrangeWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeWoolStackInitializer {
  static void init() {
    ItemTypes.ORANGE_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemOrangeWoolStack.class)
            .vanillaItem(VanillaItemId.ORANGE_WOOL)
            .build();
  }
}
