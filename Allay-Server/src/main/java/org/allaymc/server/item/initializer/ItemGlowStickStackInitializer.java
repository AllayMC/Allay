package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlowStickStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowStickStackInitializer {
  static void init() {
    ItemTypes.GLOW_STICK_TYPE = ItemTypeBuilder
            .builder(ItemGlowStickStack.class)
            .vanillaItem(VanillaItemId.GLOW_STICK)
            .build();
  }
}
