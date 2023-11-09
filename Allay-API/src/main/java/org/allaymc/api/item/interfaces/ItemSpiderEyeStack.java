package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpiderEyeStack extends ItemStack {
  ItemType<ItemSpiderEyeStack> SPIDER_EYE_TYPE = ItemTypeBuilder
          .builder(ItemSpiderEyeStack.class)
          .vanillaItem(VanillaItemId.SPIDER_EYE)
          .build();
}
