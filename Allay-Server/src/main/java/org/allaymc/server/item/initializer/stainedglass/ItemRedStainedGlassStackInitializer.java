package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemRedStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedStainedGlassStackInitializer {
  static void init() {
    ItemTypes.RED_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemRedStainedGlassStack.class)
            .vanillaItem(VanillaItemId.RED_STAINED_GLASS)
            .build();
  }
}
