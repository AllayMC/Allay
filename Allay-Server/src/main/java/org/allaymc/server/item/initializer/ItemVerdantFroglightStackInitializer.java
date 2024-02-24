package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemVerdantFroglightStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVerdantFroglightStackInitializer {
  static void init() {
    ItemTypes.VERDANT_FROGLIGHT_TYPE = ItemTypeBuilder
            .builder(ItemVerdantFroglightStack.class)
            .vanillaItem(VanillaItemId.VERDANT_FROGLIGHT)
            .build();
  }
}
