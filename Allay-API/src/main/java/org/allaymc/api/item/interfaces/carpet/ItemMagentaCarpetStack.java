package org.allaymc.api.item.interfaces.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaCarpetStack extends ItemStack {
  ItemType<ItemMagentaCarpetStack> MAGENTA_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemMagentaCarpetStack.class)
          .vanillaItem(VanillaItemId.MAGENTA_CARPET)
          .build();
}
