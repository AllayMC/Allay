package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlowBerriesStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowBerriesStackInitializer {
  static void init() {
    ItemTypes.GLOW_BERRIES_TYPE = ItemTypeBuilder
            .builder(ItemGlowBerriesStack.class)
            .vanillaItem(VanillaItemId.GLOW_BERRIES)
            .build();
  }
}
