package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardBlueStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardBlueStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_BLUE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardBlueStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_BLUE_STAINED_GLASS)
            .build();
  }
}
