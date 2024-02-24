package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemGrayStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.GRAY_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemGrayStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.GRAY_STAINED_GLASS_PANE)
            .build();
  }
}
