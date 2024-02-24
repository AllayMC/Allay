package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemOchreFroglightStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOchreFroglightStackInitializer {
  static void init() {
    ItemTypes.OCHRE_FROGLIGHT_TYPE = ItemTypeBuilder
            .builder(ItemOchreFroglightStack.class)
            .vanillaItem(VanillaItemId.OCHRE_FROGLIGHT)
            .build();
  }
}
