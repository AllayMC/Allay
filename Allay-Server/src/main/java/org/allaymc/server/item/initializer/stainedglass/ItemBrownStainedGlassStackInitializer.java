package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemBrownStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownStainedGlassStackInitializer {
  static void init() {
    ItemTypes.BROWN_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemBrownStainedGlassStack.class)
            .vanillaItem(VanillaItemId.BROWN_STAINED_GLASS)
            .build();
  }
}
