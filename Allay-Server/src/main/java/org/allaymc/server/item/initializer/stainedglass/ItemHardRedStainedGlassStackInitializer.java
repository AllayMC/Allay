package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardRedStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardRedStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_RED_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardRedStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_RED_STAINED_GLASS)
            .build();
  }
}
