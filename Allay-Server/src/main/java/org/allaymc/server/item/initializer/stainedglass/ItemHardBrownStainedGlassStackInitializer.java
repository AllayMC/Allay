package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardBrownStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardBrownStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_BROWN_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardBrownStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_BROWN_STAINED_GLASS)
            .build();
  }
}
