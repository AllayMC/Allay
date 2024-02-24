package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_STAINED_GLASS_PANE)
            .build();
  }
}
