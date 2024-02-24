package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemExposedCopperTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperTrapdoorStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemExposedCopperTrapdoorStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_COPPER_TRAPDOOR)
            .build();
  }
}
