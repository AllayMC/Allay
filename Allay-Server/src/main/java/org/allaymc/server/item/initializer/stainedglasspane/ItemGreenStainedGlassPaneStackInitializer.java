package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemGreenStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.GREEN_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemGreenStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.GREEN_STAINED_GLASS_PANE)
            .build();
  }
}
