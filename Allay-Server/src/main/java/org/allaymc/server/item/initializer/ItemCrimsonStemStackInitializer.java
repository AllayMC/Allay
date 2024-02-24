package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCrimsonStemStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonStemStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_STEM_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonStemStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_STEM)
            .build();
  }
}
