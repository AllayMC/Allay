package org.allaymc.api.item.interfaces.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadBrainCoralStack extends ItemStack {
  ItemType<ItemDeadBrainCoralStack> DEAD_BRAIN_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemDeadBrainCoralStack.class)
          .vanillaItem(VanillaItemId.DEAD_BRAIN_CORAL)
          .build();
}
