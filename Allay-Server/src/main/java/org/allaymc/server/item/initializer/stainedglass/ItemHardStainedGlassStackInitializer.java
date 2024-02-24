package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_STAINED_GLASS)
            .build();
  }
}
