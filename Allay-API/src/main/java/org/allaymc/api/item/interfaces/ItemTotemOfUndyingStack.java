package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTotemOfUndyingStack extends ItemStack {
  ItemType<ItemTotemOfUndyingStack> TOTEM_OF_UNDYING_TYPE = ItemTypeBuilder
          .builder(ItemTotemOfUndyingStack.class)
          .vanillaItem(VanillaItemId.TOTEM_OF_UNDYING)
          .build();
}
