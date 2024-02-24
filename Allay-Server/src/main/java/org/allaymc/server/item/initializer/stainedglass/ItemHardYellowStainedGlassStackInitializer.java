package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardYellowStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardYellowStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_YELLOW_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardYellowStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_YELLOW_STAINED_GLASS)
            .build();
  }
}
