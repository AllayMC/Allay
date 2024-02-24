package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemGreenStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenStainedGlassStackInitializer {
  static void init() {
    ItemTypes.GREEN_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemGreenStainedGlassStack.class)
            .vanillaItem(VanillaItemId.GREEN_STAINED_GLASS)
            .build();
  }
}
