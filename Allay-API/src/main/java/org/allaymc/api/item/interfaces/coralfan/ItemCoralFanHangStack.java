package org.allaymc.api.item.interfaces.coralfan;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanHangStack extends ItemStack {
  ItemType<ItemCoralFanHangStack> CORAL_FAN_HANG_TYPE = ItemTypeBuilder
          .builder(ItemCoralFanHangStack.class)
          .vanillaItem(VanillaItemId.CORAL_FAN_HANG)
          .build();
}
