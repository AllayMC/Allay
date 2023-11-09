package org.allaymc.api.item.interfaces.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedPlanksStack extends ItemStack {
  ItemType<ItemWarpedPlanksStack> WARPED_PLANKS_TYPE = ItemTypeBuilder
          .builder(ItemWarpedPlanksStack.class)
          .vanillaItem(VanillaItemId.WARPED_PLANKS)
          .build();
}
