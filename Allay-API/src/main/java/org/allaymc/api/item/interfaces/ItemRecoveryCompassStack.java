package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRecoveryCompassStack extends ItemStack {
  ItemType<ItemRecoveryCompassStack> RECOVERY_COMPASS_TYPE = ItemTypeBuilder
          .builder(ItemRecoveryCompassStack.class)
          .vanillaItem(VanillaItemId.RECOVERY_COMPASS)
          .build();
}
