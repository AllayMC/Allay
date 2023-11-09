package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemKelpStack extends ItemStack {
  ItemType<ItemItemKelpStack> ITEM_KELP_TYPE = ItemTypeBuilder
          .builder(ItemItemKelpStack.class)
          .vanillaItem(VanillaItemId.ITEM_KELP)
          .build();
}
