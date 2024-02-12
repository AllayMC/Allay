package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrialSpawnerStack extends ItemStack {
  ItemType<ItemTrialSpawnerStack> TRIAL_SPAWNER_TYPE = ItemTypeBuilder
          .builder(ItemTrialSpawnerStack.class)
          .vanillaItem(VanillaItemId.TRIAL_SPAWNER)
          .build();
}
