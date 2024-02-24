package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlowingobsidianStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowingobsidianStackInitializer {
  static void init() {
    ItemTypes.GLOWINGOBSIDIAN_TYPE = ItemTypeBuilder
            .builder(ItemGlowingobsidianStack.class)
            .vanillaItem(VanillaItemId.GLOWINGOBSIDIAN)
            .build();
  }
}
