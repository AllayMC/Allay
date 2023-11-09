package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndRodStack extends ItemStack {
  ItemType<ItemEndRodStack> END_ROD_TYPE = ItemTypeBuilder
          .builder(ItemEndRodStack.class)
          .vanillaItem(VanillaItemId.END_ROD)
          .build();
}
