package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayDyeStack extends ItemStack {
  ItemType<ItemLightGrayDyeStack> LIGHT_GRAY_DYE_TYPE = ItemTypeBuilder
          .builder(ItemLightGrayDyeStack.class)
          .vanillaItem(VanillaItemId.LIGHT_GRAY_DYE)
          .build();
}
