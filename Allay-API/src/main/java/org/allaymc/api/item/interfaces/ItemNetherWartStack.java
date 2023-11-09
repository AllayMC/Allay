package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherWartStack extends ItemStack {
  ItemType<ItemNetherWartStack> NETHER_WART_TYPE = ItemTypeBuilder
          .builder(ItemNetherWartStack.class)
          .vanillaItem(VanillaItemId.NETHER_WART)
          .build();
}
