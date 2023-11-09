package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnifferEggStack extends ItemStack {
  ItemType<ItemSnifferEggStack> SNIFFER_EGG_TYPE = ItemTypeBuilder
          .builder(ItemSnifferEggStack.class)
          .vanillaItem(VanillaItemId.SNIFFER_EGG)
          .build();
}
