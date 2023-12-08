package org.allaymc.api.item.interfaces.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakPlanksStack extends ItemStack {
  ItemType<ItemDarkOakPlanksStack> DARK_OAK_PLANKS_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakPlanksStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_PLANKS)
          .build();
}
