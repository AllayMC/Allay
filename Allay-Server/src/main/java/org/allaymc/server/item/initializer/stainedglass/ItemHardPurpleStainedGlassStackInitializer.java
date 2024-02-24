package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardPurpleStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardPurpleStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_PURPLE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardPurpleStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_PURPLE_STAINED_GLASS)
            .build();
  }
}
