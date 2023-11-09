package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeDyeStack extends ItemStack {
  ItemType<ItemLimeDyeStack> LIME_DYE_TYPE = ItemTypeBuilder
          .builder(ItemLimeDyeStack.class)
          .vanillaItem(VanillaItemId.LIME_DYE)
          .build();
}
