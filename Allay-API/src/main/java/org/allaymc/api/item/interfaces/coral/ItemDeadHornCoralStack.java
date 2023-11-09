package org.allaymc.api.item.interfaces.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadHornCoralStack extends ItemStack {
  ItemType<ItemDeadHornCoralStack> DEAD_HORN_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemDeadHornCoralStack.class)
          .vanillaItem(VanillaItemId.DEAD_HORN_CORAL)
          .build();
}
