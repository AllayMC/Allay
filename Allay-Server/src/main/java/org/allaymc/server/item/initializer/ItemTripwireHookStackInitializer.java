package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTripwireHookStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTripwireHookStackInitializer {
  static void init() {
    ItemTypes.TRIPWIRE_HOOK_TYPE = ItemTypeBuilder
            .builder(ItemTripwireHookStack.class)
            .vanillaItem(VanillaItemId.TRIPWIRE_HOOK)
            .build();
  }
}
