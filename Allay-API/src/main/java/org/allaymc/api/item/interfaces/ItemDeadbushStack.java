package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadbushStack extends ItemStack {
  ItemType<ItemDeadbushStack> DEADBUSH_TYPE = ItemTypeBuilder
          .builder(ItemDeadbushStack.class)
          .vanillaItem(VanillaItemId.DEADBUSH)
          .build();
}
