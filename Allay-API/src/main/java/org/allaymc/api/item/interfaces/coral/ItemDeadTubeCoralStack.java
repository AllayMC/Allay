package org.allaymc.api.item.interfaces.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadTubeCoralStack extends ItemStack {
  ItemType<ItemDeadTubeCoralStack> DEAD_TUBE_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemDeadTubeCoralStack.class)
          .vanillaItem(VanillaItemId.DEAD_TUBE_CORAL)
          .build();
}
