package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemPurpleStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleStainedGlassStackInitializer {
  static void init() {
    ItemTypes.PURPLE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemPurpleStainedGlassStack.class)
            .vanillaItem(VanillaItemId.PURPLE_STAINED_GLASS)
            .build();
  }
}
