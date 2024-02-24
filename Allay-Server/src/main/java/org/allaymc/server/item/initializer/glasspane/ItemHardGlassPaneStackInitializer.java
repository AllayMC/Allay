package org.allaymc.server.item.initializer.glasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.glasspane.ItemHardGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_GLASS_PANE)
            .build();
  }
}
