package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoalStack extends ItemStack {
  ItemType<ItemCoalStack> COAL_TYPE = ItemTypeBuilder
          .builder(ItemCoalStack.class)
          .vanillaItem(VanillaItemId.COAL)
          .build();
}
