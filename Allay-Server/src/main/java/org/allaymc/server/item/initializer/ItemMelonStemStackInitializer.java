package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMelonStemStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonStemStackInitializer {
  static void init() {
    ItemTypes.MELON_STEM_TYPE = ItemTypeBuilder
            .builder(ItemMelonStemStack.class)
            .vanillaItem(VanillaItemId.MELON_STEM)
            .build();
  }
}
