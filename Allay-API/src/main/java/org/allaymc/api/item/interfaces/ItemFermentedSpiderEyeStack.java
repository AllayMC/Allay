package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFermentedSpiderEyeStack extends ItemStack {
  ItemType<ItemFermentedSpiderEyeStack> FERMENTED_SPIDER_EYE_TYPE = ItemTypeBuilder
          .builder(ItemFermentedSpiderEyeStack.class)
          .vanillaItem(VanillaItemId.FERMENTED_SPIDER_EYE)
          .build();
}
