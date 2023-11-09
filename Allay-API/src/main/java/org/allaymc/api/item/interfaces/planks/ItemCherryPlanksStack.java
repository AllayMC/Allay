package org.allaymc.api.item.interfaces.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryPlanksStack extends ItemStack {
  ItemType<ItemCherryPlanksStack> CHERRY_PLANKS_TYPE = ItemTypeBuilder
          .builder(ItemCherryPlanksStack.class)
          .vanillaItem(VanillaItemId.CHERRY_PLANKS)
          .build();
}
