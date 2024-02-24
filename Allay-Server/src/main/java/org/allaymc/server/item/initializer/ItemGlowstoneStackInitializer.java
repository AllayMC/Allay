package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlowstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowstoneStackInitializer {
  static void init() {
    ItemTypes.GLOWSTONE_TYPE = ItemTypeBuilder
            .builder(ItemGlowstoneStack.class)
            .vanillaItem(VanillaItemId.GLOWSTONE)
            .build();
  }
}
