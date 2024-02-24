package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlowstoneDustStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowstoneDustStackInitializer {
  static void init() {
    ItemTypes.GLOWSTONE_DUST_TYPE = ItemTypeBuilder
            .builder(ItemGlowstoneDustStack.class)
            .vanillaItem(VanillaItemId.GLOWSTONE_DUST)
            .build();
  }
}
