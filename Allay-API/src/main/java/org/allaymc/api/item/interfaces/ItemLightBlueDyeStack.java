package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueDyeStack extends ItemStack {
  ItemType<ItemLightBlueDyeStack> LIGHT_BLUE_DYE_TYPE = ItemTypeBuilder
          .builder(ItemLightBlueDyeStack.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_DYE)
          .build();
}
