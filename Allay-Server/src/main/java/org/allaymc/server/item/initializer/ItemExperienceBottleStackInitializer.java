package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemExperienceBottleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExperienceBottleStackInitializer {
  static void init() {
    ItemTypes.EXPERIENCE_BOTTLE_TYPE = ItemTypeBuilder
            .builder(ItemExperienceBottleStack.class)
            .vanillaItem(VanillaItemId.EXPERIENCE_BOTTLE)
            .build();
  }
}
