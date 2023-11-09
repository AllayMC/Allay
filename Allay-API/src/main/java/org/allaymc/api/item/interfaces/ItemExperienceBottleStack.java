package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExperienceBottleStack extends ItemStack {
  ItemType<ItemExperienceBottleStack> EXPERIENCE_BOTTLE_TYPE = ItemTypeBuilder
          .builder(ItemExperienceBottleStack.class)
          .vanillaItem(VanillaItemId.EXPERIENCE_BOTTLE)
          .build();
}
