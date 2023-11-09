package org.allaymc.api.item.interfaces.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTubeCoralStack extends ItemStack {
  ItemType<ItemTubeCoralStack> TUBE_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemTubeCoralStack.class)
          .vanillaItem(VanillaItemId.TUBE_CORAL)
          .build();
}
