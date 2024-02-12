package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTrialKeyStack extends ItemStack {
  ItemType<ItemTrialKeyStack> TRIAL_KEY_TYPE = ItemTypeBuilder
          .builder(ItemTrialKeyStack.class)
          .vanillaItem(VanillaItemId.TRIAL_KEY)
          .build();
}
