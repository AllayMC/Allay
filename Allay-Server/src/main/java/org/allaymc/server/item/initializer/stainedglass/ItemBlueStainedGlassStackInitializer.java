package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemBlueStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueStainedGlassStackInitializer {
  static void init() {
    ItemTypes.BLUE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemBlueStainedGlassStack.class)
            .vanillaItem(VanillaItemId.BLUE_STAINED_GLASS)
            .build();
  }
}
