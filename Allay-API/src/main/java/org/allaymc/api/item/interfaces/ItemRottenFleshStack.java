package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRottenFleshStack extends ItemStack {
  ItemType<ItemRottenFleshStack> ROTTEN_FLESH_TYPE = ItemTypeBuilder
          .builder(ItemRottenFleshStack.class)
          .vanillaItem(VanillaItemId.ROTTEN_FLESH)
          .build();
}
