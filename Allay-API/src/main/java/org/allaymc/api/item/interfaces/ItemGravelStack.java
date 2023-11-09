package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGravelStack extends ItemStack {
  ItemType<ItemGravelStack> GRAVEL_TYPE = ItemTypeBuilder
          .builder(ItemGravelStack.class)
          .vanillaItem(VanillaItemId.GRAVEL)
          .build();
}
