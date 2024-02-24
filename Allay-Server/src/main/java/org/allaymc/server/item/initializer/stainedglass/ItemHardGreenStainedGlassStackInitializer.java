package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardGreenStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardGreenStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_GREEN_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardGreenStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_GREEN_STAINED_GLASS)
            .build();
  }
}
