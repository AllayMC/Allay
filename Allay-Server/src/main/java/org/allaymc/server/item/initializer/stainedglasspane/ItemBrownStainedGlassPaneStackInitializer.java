package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemBrownStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.BROWN_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemBrownStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.BROWN_STAINED_GLASS_PANE)
            .build();
  }
}
