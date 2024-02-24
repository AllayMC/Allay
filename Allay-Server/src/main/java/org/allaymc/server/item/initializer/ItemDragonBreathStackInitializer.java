package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDragonBreathStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDragonBreathStackInitializer {
  static void init() {
    ItemTypes.DRAGON_BREATH_TYPE = ItemTypeBuilder
            .builder(ItemDragonBreathStack.class)
            .vanillaItem(VanillaItemId.DRAGON_BREATH)
            .build();
  }
}
