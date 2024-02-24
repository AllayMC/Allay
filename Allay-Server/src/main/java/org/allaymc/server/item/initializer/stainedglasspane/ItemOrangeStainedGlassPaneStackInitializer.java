package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemOrangeStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.ORANGE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemOrangeStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.ORANGE_STAINED_GLASS_PANE)
            .build();
  }
}
