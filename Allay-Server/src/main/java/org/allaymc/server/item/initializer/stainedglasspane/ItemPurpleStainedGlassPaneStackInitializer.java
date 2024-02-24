package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemPurpleStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.PURPLE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemPurpleStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.PURPLE_STAINED_GLASS_PANE)
            .build();
  }
}
