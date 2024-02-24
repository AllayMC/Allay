package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemShroomlightStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShroomlightStackInitializer {
  static void init() {
    ItemTypes.SHROOMLIGHT_TYPE = ItemTypeBuilder
            .builder(ItemShroomlightStack.class)
            .vanillaItem(VanillaItemId.SHROOMLIGHT)
            .build();
  }
}
