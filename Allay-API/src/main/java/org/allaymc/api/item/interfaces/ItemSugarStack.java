package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSugarStack extends ItemStack {
  ItemType<ItemSugarStack> SUGAR_TYPE = ItemTypeBuilder
          .builder(ItemSugarStack.class)
          .vanillaItem(VanillaItemId.SUGAR)
          .build();
}
