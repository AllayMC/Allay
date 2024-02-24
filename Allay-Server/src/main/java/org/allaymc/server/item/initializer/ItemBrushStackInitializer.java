package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBrushStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrushStackInitializer {
  static void init() {
    ItemTypes.BRUSH_TYPE = ItemTypeBuilder
            .builder(ItemBrushStack.class)
            .vanillaItem(VanillaItemId.BRUSH)
            .build();
  }
}
