package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWarpedFungusOnAStickStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFungusOnAStickStackInitializer {
  static void init() {
    ItemTypes.WARPED_FUNGUS_ON_A_STICK_TYPE = ItemTypeBuilder
            .builder(ItemWarpedFungusOnAStickStack.class)
            .vanillaItem(VanillaItemId.WARPED_FUNGUS_ON_A_STICK)
            .build();
  }
}
