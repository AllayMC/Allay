package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCarrotOnAStickStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarrotOnAStickStackInitializer {
  static void init() {
    ItemTypes.CARROT_ON_A_STICK_TYPE = ItemTypeBuilder
            .builder(ItemCarrotOnAStickStack.class)
            .vanillaItem(VanillaItemId.CARROT_ON_A_STICK)
            .build();
  }
}
