package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherHelmetStack extends ItemStack {
  ItemType<ItemLeatherHelmetStack> LEATHER_HELMET_TYPE = ItemTypeBuilder
          .builder(ItemLeatherHelmetStack.class)
          .vanillaItem(VanillaItemId.LEATHER_HELMET)
          .build();
}
