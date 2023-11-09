package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoneMealStack extends ItemStack {
  ItemType<ItemBoneMealStack> BONE_MEAL_TYPE = ItemTypeBuilder
          .builder(ItemBoneMealStack.class)
          .vanillaItem(VanillaItemId.BONE_MEAL)
          .build();
}
