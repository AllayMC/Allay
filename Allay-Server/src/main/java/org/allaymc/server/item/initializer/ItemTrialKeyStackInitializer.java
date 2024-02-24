package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTrialKeyStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrialKeyStackInitializer {
  static void init() {
    ItemTypes.TRIAL_KEY_TYPE = ItemTypeBuilder
            .builder(ItemTrialKeyStack.class)
            .vanillaItem(VanillaItemId.TRIAL_KEY)
            .build();
  }
}
