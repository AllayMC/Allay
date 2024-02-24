package org.allaymc.server.item.initializer.glass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.glass.ItemGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlassStackInitializer {
  static void init() {
    ItemTypes.GLASS_TYPE = ItemTypeBuilder
            .builder(ItemGlassStack.class)
            .vanillaItem(VanillaItemId.GLASS)
            .build();
  }
}
