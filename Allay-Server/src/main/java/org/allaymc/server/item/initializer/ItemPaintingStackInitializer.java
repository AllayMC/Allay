package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPaintingStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPaintingStackInitializer {
  static void init() {
    ItemTypes.PAINTING_TYPE = ItemTypeBuilder
            .builder(ItemPaintingStack.class)
            .vanillaItem(VanillaItemId.PAINTING)
            .build();
  }
}
