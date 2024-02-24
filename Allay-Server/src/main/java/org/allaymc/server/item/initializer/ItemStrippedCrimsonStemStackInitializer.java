package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStrippedCrimsonStemStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCrimsonStemStackInitializer {
  static void init() {
    ItemTypes.STRIPPED_CRIMSON_STEM_TYPE = ItemTypeBuilder
            .builder(ItemStrippedCrimsonStemStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_CRIMSON_STEM)
            .build();
  }
}
