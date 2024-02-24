package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBoneMealStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoneMealStackInitializer {
  static void init() {
    ItemTypes.BONE_MEAL_TYPE = ItemTypeBuilder
            .builder(ItemBoneMealStack.class)
            .vanillaItem(VanillaItemId.BONE_MEAL)
            .build();
  }
}
