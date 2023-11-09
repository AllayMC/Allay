package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherBootsStack extends ItemStack {
  ItemType<ItemLeatherBootsStack> LEATHER_BOOTS_TYPE = ItemTypeBuilder
          .builder(ItemLeatherBootsStack.class)
          .vanillaItem(VanillaItemId.LEATHER_BOOTS)
          .build();
}
