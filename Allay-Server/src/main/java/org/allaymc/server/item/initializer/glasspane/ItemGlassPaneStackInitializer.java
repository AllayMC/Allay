package org.allaymc.server.item.initializer.glasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.glasspane.ItemGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlassPaneStackInitializer {
  static void init() {
    ItemTypes.GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemGlassPaneStack.class)
            .vanillaItem(VanillaItemId.GLASS_PANE)
            .build();
  }
}
