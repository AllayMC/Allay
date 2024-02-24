package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemDarkOakTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakTrapdoorStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakTrapdoorStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_TRAPDOOR)
            .build();
  }
}
