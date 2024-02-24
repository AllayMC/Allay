package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPumpkinStemStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinStemStackInitializer {
  static void init() {
    ItemTypes.PUMPKIN_STEM_TYPE = ItemTypeBuilder
            .builder(ItemPumpkinStemStack.class)
            .vanillaItem(VanillaItemId.PUMPKIN_STEM)
            .build();
  }
}
