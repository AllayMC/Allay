package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemYellowStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowStainedGlassStackInitializer {
  static void init() {
    ItemTypes.YELLOW_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemYellowStainedGlassStack.class)
            .vanillaItem(VanillaItemId.YELLOW_STAINED_GLASS)
            .build();
  }
}
