package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemWeatheredCopperTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCopperTrapdoorStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCopperTrapdoorStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_COPPER_TRAPDOOR)
            .build();
  }
}
