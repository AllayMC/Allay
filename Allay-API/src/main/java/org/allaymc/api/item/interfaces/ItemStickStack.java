package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickStack extends ItemStack {
  ItemType<ItemStickStack> STICK_TYPE = ItemTypeBuilder
          .builder(ItemStickStack.class)
          .vanillaItem(VanillaItemId.STICK)
          .build();
}
