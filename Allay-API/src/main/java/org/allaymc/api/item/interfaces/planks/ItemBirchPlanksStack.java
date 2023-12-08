package org.allaymc.api.item.interfaces.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchPlanksStack extends ItemStack {
  ItemType<ItemBirchPlanksStack> BIRCH_PLANKS_TYPE = ItemTypeBuilder
          .builder(ItemBirchPlanksStack.class)
          .vanillaItem(VanillaItemId.BIRCH_PLANKS)
          .build();
}
