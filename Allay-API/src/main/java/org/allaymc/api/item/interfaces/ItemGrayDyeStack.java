package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayDyeStack extends ItemStack {
  ItemType<ItemGrayDyeStack> GRAY_DYE_TYPE = ItemTypeBuilder
          .builder(ItemGrayDyeStack.class)
          .vanillaItem(VanillaItemId.GRAY_DYE)
          .build();
}
