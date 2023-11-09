package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenDyeStack extends ItemStack {
  ItemType<ItemGreenDyeStack> GREEN_DYE_TYPE = ItemTypeBuilder
          .builder(ItemGreenDyeStack.class)
          .vanillaItem(VanillaItemId.GREEN_DYE)
          .build();
}
