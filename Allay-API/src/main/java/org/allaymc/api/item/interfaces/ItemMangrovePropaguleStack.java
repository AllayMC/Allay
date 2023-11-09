package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangrovePropaguleStack extends ItemStack {
  ItemType<ItemMangrovePropaguleStack> MANGROVE_PROPAGULE_TYPE = ItemTypeBuilder
          .builder(ItemMangrovePropaguleStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_PROPAGULE)
          .build();
}
