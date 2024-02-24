package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemOrangeStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeStainedGlassStackInitializer {
  static void init() {
    ItemTypes.ORANGE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemOrangeStainedGlassStack.class)
            .vanillaItem(VanillaItemId.ORANGE_STAINED_GLASS)
            .build();
  }
}
