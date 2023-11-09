package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateStack extends ItemStack {
  ItemType<ItemPolishedDeepslateStack> POLISHED_DEEPSLATE_TYPE = ItemTypeBuilder
          .builder(ItemPolishedDeepslateStack.class)
          .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE)
          .build();
}
