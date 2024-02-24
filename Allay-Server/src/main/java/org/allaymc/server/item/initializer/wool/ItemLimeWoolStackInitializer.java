package org.allaymc.server.item.initializer.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.wool.ItemLimeWoolStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeWoolStackInitializer {
  static void init() {
    ItemTypes.LIME_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemLimeWoolStack.class)
            .vanillaItem(VanillaItemId.LIME_WOOL)
            .build();
  }
}
