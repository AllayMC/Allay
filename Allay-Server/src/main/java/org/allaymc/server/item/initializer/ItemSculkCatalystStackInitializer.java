package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSculkCatalystStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkCatalystStackInitializer {
  static void init() {
    ItemTypes.SCULK_CATALYST_TYPE = ItemTypeBuilder
            .builder(ItemSculkCatalystStack.class)
            .vanillaItem(VanillaItemId.SCULK_CATALYST)
            .build();
  }
}
