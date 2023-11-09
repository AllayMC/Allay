package org.allaymc.api.item.interfaces.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadFireCoralStack extends ItemStack {
  ItemType<ItemDeadFireCoralStack> DEAD_FIRE_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemDeadFireCoralStack.class)
          .vanillaItem(VanillaItemId.DEAD_FIRE_CORAL)
          .build();
}
