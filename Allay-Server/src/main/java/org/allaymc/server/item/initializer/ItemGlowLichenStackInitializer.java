package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlowLichenStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowLichenStackInitializer {
  static void init() {
    ItemTypes.GLOW_LICHEN_TYPE = ItemTypeBuilder
            .builder(ItemGlowLichenStack.class)
            .vanillaItem(VanillaItemId.GLOW_LICHEN)
            .build();
  }
}
