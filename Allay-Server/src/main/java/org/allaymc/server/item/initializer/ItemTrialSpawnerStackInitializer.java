package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTrialSpawnerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrialSpawnerStackInitializer {
  static void init() {
    ItemTypes.TRIAL_SPAWNER_TYPE = ItemTypeBuilder
            .builder(ItemTrialSpawnerStack.class)
            .vanillaItem(VanillaItemId.TRIAL_SPAWNER)
            .build();
  }
}
